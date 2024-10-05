package org.godpro.godpro_server.domain.project.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.dao.ProjectRetrieve;
import org.godpro.godpro_server.domain.project.dao.ReceiveProject;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {
 private final ProjectRepository projectRepository;

 // 모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수 반환
 public ApiResponse<List<ProjectRetrieve>> retrieveUnrecruitedProjects() {
  List<ProjectRetrieve> project = projectRepository.findByIsRecruitedFalse();
  return ApiResponse.ok("모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수를 정상적으로 조회했습니다.", project);
 }

 // 프로젝트 단권 조회
 public ApiResponse<ReceiveProject> receiveProjectById(Long projectId) {
   Optional<ReceiveProject> optionalProject = projectRepository.findProjectById(projectId);

   //프로젝트 유무 검사
   if (optionalProject.isEmpty()) {
       return ApiResponse.withError(ErrorCode.PROJECT_NOT_FOUND);
  }
   ReceiveProject receiveProject = optionalProject.get();
   return ApiResponse.ok("프로젝트를 성공적으로 조회했습니다.", receiveProject);
 }
}
