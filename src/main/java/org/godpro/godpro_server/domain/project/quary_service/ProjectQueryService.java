package org.godpro.godpro_server.domain.project.quary_service;

import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectQueryService {

    private static ProjectRepository projectRepository;

    @Autowired
    public ProjectQueryService(ProjectRepository projectRepository) {
        ProjectQueryService.projectRepository = projectRepository; // Setting the static repository instance
    }

    public static Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }


}
