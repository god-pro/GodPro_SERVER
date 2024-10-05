package org.godpro.godpro_server.domain.application.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.application.dao.ApplicationRepository;
import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.application.dto.request.ApplyRequestDto;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.dao.UserRepository;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ApplicationRepository applicationRepository;

    public ApiResponse<Application> applyToProject(Long userId, Long projectId, ApplyRequestDto applyRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }
        User user = optionalUser.get();

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
        }
        Project project = optionalProject.get();

        Application application = applyRequestDto.toEntity(project, user);
        Application savedApplication = applicationRepository.save(application);

        return ApiResponse.ok("프로젝트에 성공적으로 지원하였습니다.", savedApplication);
    }
}
