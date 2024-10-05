package org.godpro.godpro_server.domain.project.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.application.ProjectService;
import org.godpro.godpro_server.domain.project.dao.ProjectRetrieve;
import org.godpro.godpro_server.domain.project.application.ProjectQueryService;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.project.dto.request.CreateProjectRequestDto;
import org.godpro.godpro_server.domain.project.dto.request.CreateProjectServiceRequestDto;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectQueryService projectQueryService;

    // 모집이 완료되지 않은 프로젝트 목록 조회
    @Operation(summary = "모집이 완료되지 않은 프로젝트 목록 조회 API")
    @GetMapping("/unrecruited")
    public ApiResponse<List<ProjectRetrieve>> retrieveUnrecruitedProjects() {
        return projectQueryService.retrieveUnrecruitedProjects();
    }

    @Operation(summary = "프로젝트 삭제 API")
    @DeleteMapping("/{projectId}")
    public ApiResponse<String> deleteProject(@RequestHeader("Authorization") String userId,
                                             @PathVariable("projectId") Long projectId) {
        return projectService.deleteProject(userId, projectId);
    }

    @Operation(summary = "프로젝트 모집 마감 API")
    @PostMapping("/{projectId}/close-recruitment")
    public ApiResponse<String> closeRecruitment(@RequestHeader("Authorization") String userId,
                                                 @PathVariable("projectId") Long projectId) {
        return projectService.closeRecruitment(userId, projectId);
    }

    @Operation(summary = "프로젝트 생성 API")
    @PostMapping
    public ApiResponse<String> createProject(@RequestHeader("Authorization") String userId,
                                              @RequestBody CreateProjectRequestDto dto) {
        return projectService.createProject(userId, dto.toServiceRequest());
    }

//    @Operation(summary = "특정 프로젝트 파트별 지원자 조회")
//    @GetMapping("/applicant/part")
//    public ApiResponse<User> retrieveApplicantByPart(@PathVariable("projectId") Long projectId) {
//        return projectService.retrieveApplicantByPart(projectId);
//    }

}
