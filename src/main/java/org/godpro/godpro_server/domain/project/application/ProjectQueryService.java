package org.godpro.godpro_server.domain.project.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.project.dao.ProjectRepository;
import org.godpro.godpro_server.domain.project.dao.ProjectRetrieve;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {
 private final ProjectRepository projectRepository;

 // 모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수 반환
 public ApiResponse<List<ProjectRetrieve>> retrieveUnrecruitedProjects() {
  List<ProjectRetrieve> project = projectRepository.findByIsRecruitedFalse();
  return ApiResponse.ok("모집이 완료되지 않은 프로젝트들의 이름, 소개, 파트별 인원수를 정상적으로 조회했습니다.", project);
 }
}
