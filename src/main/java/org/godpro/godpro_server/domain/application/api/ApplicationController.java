package org.godpro.godpro_server.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.application.application.ApplicationQueryService;
import org.godpro.godpro_server.domain.application.application.ApplicationService;
import org.godpro.godpro_server.domain.application.dao.ApplicationDetail;
import org.godpro.godpro_server.domain.application.dao.ProjectApplicantRetrieve;
import org.godpro.godpro_server.domain.application.dto.request.ApplyRequestDto;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationQueryService applicationQueryService;

    @Operation(summary = "프로젝트 지원 API")
    @PostMapping("/{projectId}/applies")
    public ApiResponse<String> applyToProject(@RequestHeader("Authorization") String userId,
                                              @PathVariable("projectId") Long projectId,
                                              @RequestBody @Valid ApplyRequestDto applyRequestDto) {
        return applicationService.applyToProject(userId, projectId, applyRequestDto);
    }

    @Operation(summary = "프로젝트 지원자 합격 처리 API")
    @PostMapping("/{projectId}/applications/{applicationId}/accepts")
    public ApiResponse<String> acceptApplication(@RequestHeader("Authorization") String userId,
                                                 @PathVariable("projectId") Long projectId,
                                                 @PathVariable("applicationId") Long applicationId) {
        return applicationService.acceptApplication(userId, projectId, applicationId);
    }

    @Operation(summary = "프로젝트 지원 단건 조회 API")
    @GetMapping("/{projectId}/applications/{applicationId}")
    public ApiResponse<ApplicationDetail> findApplicationDetail(@RequestHeader("Authorization") String userId,
                                                                @PathVariable("projectId") Long projectId,
                                                                @PathVariable("applicationId") Long applicationId) {
        return applicationQueryService.findApplicationDetail(userId, projectId, applicationId);
    }

    @Operation(summary = "프로젝트 지원 전체 조회 API")
    @GetMapping("/{projectId}/applications")
    public ApiResponse<List<ProjectApplicantRetrieve>> findProjectApplicant(@RequestHeader("Authorization") String userId,
                                                                            @PathVariable("projectId") Long projectId) {
        return applicationQueryService.findProjectApplicant(userId, projectId);
    }
}
