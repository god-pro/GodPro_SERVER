package org.godpro.godpro_server.domain.project.service;

import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.project.dto.request.CreateProjectServiceRequestDto;
import org.godpro.godpro_server.domain.user.dao.UserRepository;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ApiResponse<Project> createProject(Long userId,CreateProjectServiceRequestDto projectDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ApiResponse.withError(ErrorCode.USER_NOT_FOUND);
        }
        User user = optionalUser.get();
        Project project = projectDto.toEntity(user);
        Project saved = projectRepository.save(project);
        return ApiResponse.ok(saved);
    }





}
