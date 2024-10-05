package org.godpro.godpro_server.domain.project.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.dao.UserCreatedProjectRetrieve;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.dao.ProjectRetrieve;
import org.godpro.godpro_server.domain.project.dao.ReceiveProject;
import org.godpro.godpro_server.domain.user.application.UserService;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    // 모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수 반환
    public ApiResponse<List<ProjectRetrieve>> retrieveUnrecruitedProjects() {
        List<ProjectRetrieve> project = projectRepository.findByIsRecruitedFalse();
        return ApiResponse.ok("모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수를 정상적으로 조회했습니다.", project);
    }

    // 프로젝트 단권 조회
    public ApiResponse<ReceiveProject> receiveProjectById(String userId, Long projectId) {
        if (userService.isExisted(userId)) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }

        Optional<ReceiveProject> optionalProject = projectRepository.findProjectById(projectId);

        //프로젝트 유무 검사
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }
        ReceiveProject receiveProject = optionalProject.get();
        return ApiResponse.ok("프로젝트를 성공적으로 조회했습니다.", receiveProject);
    }

    public ApiResponse<List<UserCreatedProjectRetrieve>> retrieveUserCreatedProjects(String kakaoId) {
        ApiResponse<User> response = userService.retrieveUser(kakaoId);
        if (response.getStatus().equals(HttpStatus.NOT_FOUND)) return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        Long userId = response.getData().getId();

        List<UserCreatedProjectRetrieve> userProjects = projectRepository.findUserProjects(userId);
        return ApiResponse.ok("해당 유저가 생성한 프로젝트를 성공적으로 전체 조회했습니다.", userProjects);

    }
}
