package org.godpro.godpro_server.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.application.application.ApplicationService;
import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.application.dto.request.ApplyRequestDto;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "프로젝트 지원 API")
    @PostMapping("/{projectId}/apply")
    public ApiResponse<String> applyToProject(@RequestHeader("Authorization") String userId,
                                                   @PathVariable("projectId") Long projectId,
                                                   @RequestBody @Valid ApplyRequestDto applyRequestDto) {
        return applicationService.applyToProject(userId, projectId, applyRequestDto);
    }
}
