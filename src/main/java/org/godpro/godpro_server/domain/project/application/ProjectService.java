package org.godpro.godpro_server.domain.project.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.project.dto.request.CreateProjectServiceRequestDto;
import org.godpro.godpro_server.domain.user.application.UserService;
import org.godpro.godpro_server.domain.user.dao.UserRepository;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ApiResponse<String> deleteProject(String userId, Long projectId) {
        if (userService.isExisted(userId)) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }
        Project project = optionalProject.get();

        // 프로젝트 생성한 사람인지 검증
        if (!project.getCreator().getId().equals(userId)) {
            return ApiResponse.withError(ErrorCode.NOT_PROJECT_CREATOR_ERROR);
        }

        projectRepository.delete(project );
        return ApiResponse.ok("프로젝트가 성공적으로 삭제되었습니다.");
    }

    @Transactional
    public ApiResponse<Project> updateProject(String userId, Long projectId, CreateProjectServiceRequestDto projectDto) {
        // 사용자 ID로 사용자 찾기
        if (userService.isExisted(userId)) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }

        // 프로젝트 ID로 프로젝트 찾기
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }

        Project project = optionalProject.get();

        // 사용자가 프로젝트 생성자인지 확인
        if (!project.getCreator().getId().equals(userId)) {
            return ApiResponse.withError(ErrorCode.NOT_PROJECT_CREATOR_ERROR);
        }

        // 프로젝트에 지원자가 있는지 확인
        if (!project.getApplications().isEmpty()) {
            return ApiResponse.withError(ErrorCode.CANNOT_UPDATE_PROJECT_WITH_APPLICANTS);
        }

        // 프로젝트 세부 정보 업데이트
        project.updateProject(
                projectDto.name(),
                projectDto.shortDescription(),
                projectDto.detailDescription(),
                projectDto.back(),
                projectDto.front(),
                projectDto.pm(),
                projectDto.design(),
                projectDto.eta()
        );

        // 업데이트된 프로젝트 저장
        Project updatedProject = projectRepository.save(project);
        return ApiResponse.ok(updatedProject);
    }

    public ApiResponse<String> closeRecruitment(String userId, Long projectId) {
        if (userService.isExisted(userId)) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }
        Project project = optionalProject.get();

        // 프로젝트 생성한 사람인지 검증
        if (!project.getCreator().getId().equals(userId)) {
            return ApiResponse.withError(ErrorCode.NOT_PROJECT_CREATOR_ERROR);
        }

        project.updateIsRecruited(true);
        Project saveProject = projectRepository.save(project);

        return ApiResponse.ok("프로젝트 모집을 성공적으로 종료하였습니다.");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleOldTrashFilesDeletion() {
        List<Project> endDatePassedProjectList = projectRepository.findAllByRecruitmentEndDatePassed(LocalDateTime.now());
        if (!endDatePassedProjectList.isEmpty()) {
            for (Project project : endDatePassedProjectList) {
                project.updateIsRecruited(true);
                projectRepository.save(project);
            }
        }
    }

    public ApiResponse<String> createProject(String userId, CreateProjectServiceRequestDto projectDto) {
        ApiResponse<User> response = userService.retrieveUser(userId);
        if(response.getStatus().equals(HttpStatus.NOT_FOUND)) return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        User user = response.getData();
        Project project = projectDto.toEntity(user);
        Project saved = projectRepository.save(project);
        return ApiResponse.ok("프로젝트를 성공적으로 생성했습니다.");
    }

//    public ApiResponse<User> retrieveApplicantByPart(Long projectId) {
//        Optional<Project> optionalProject = projectRepository.findById(projectId);
//        if(optionalProject.isEmpty()) {
//            return ApiResponse.withError(ErrorCode.INVALID_PROJECT_ID);
//        }
//        Project project = optionalProject.get();
//    }
}
