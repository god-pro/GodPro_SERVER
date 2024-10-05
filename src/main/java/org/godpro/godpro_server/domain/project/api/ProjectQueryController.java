package org.godpro.godpro_server.domain.project.api;

import lombok.AllArgsConstructor;
import org.godpro.godpro_server.domain.project.application.ProjectQueryService;
import org.godpro.godpro_server.domain.project.dao.ReceiveProject;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/projects")
public class ProjectQueryController {

    private ProjectQueryService projectQueryService;

    @GetMapping("/{id}")
    public ApiResponse<ReceiveProject> receiveProjectById(@PathVariable Long id) {
        return projectQueryService.receiveProjectById(id);
    }
}
