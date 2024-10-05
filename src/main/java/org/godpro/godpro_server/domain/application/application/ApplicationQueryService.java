package org.godpro.godpro_server.domain.application.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.application.dao.ApplicationDetail;
import org.godpro.godpro_server.domain.application.dao.ApplicationRepository;
import org.godpro.godpro_server.domain.application.dao.ProjectApplicantRetrieve;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.application.UserService;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ApiResponse<ApplicationDetail> findApplicationDetail(String userId, Long projectId, Long applicationId) {
        ApiResponse<User> response = userService.retrieveUser(userId);
        if (response.getStatus().equals(HttpStatus.NOT_FOUND)) return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        User user = response.getData();

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }
        Project project = optionalProject.get();

        if (!applicationRepository.existsById(applicationId)) {
            return ApiResponse.withError(ErrorCode.APPLICATION_NOT_FOUND_ERROR);
        }

        if (project.getCreator().getId().equals(user.getId())) {
            return ApiResponse.withError(ErrorCode.NOT_PROJECT_CREATOR_ERROR);
        }

        ApplicationDetail applicationDetail = applicationRepository.findApplicationDetail(projectId, applicationId);
        return ApiResponse.ok("지원 정보를 성공적으로 조회하였습니다.", applicationDetail);
    }

    public ApiResponse<List<ProjectApplicantRetrieve>> findProjectApplicant(String userId, Long projectId) {
        if (!userService.isExisted(userId)) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }

        if (!projectRepository.existsById(projectId)) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }

        List<ProjectApplicantRetrieve> applicants = applicationRepository.findApplicantsByProjectId(projectId);
        return ApiResponse.ok("프로젝트 지원자 정보를 성공적으로 전체 조회하였습니다.", applicants);
    }
}
