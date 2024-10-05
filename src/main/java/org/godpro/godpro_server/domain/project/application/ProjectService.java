package org.godpro.godpro_server.domain.project.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.project.dto.request.CreateProjectServiceRequestDto;
import org.godpro.godpro_server.domain.user.dao.UserRepository;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
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
    private final UserRepository userRepository;

    public ApiResponse<String> deleteProject(Long userId, Long projectId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
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

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
    public ApiResponse<String> closeRecruitment(String userId, Long projectId) {
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

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
    public ApiResponse<String> createProject(String userId, CreateProjectServiceRequestDto projectDto) {
        }
        User user = optionalUser.get();
        Project project = projectDto.toEntity(user);
        Project saved = projectRepository.save(project);
        return ApiResponse.ok("프로젝트를 성공적으로 생성했습니다.");
    }
}
