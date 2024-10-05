package org.godpro.godpro_server.domain.project.dao;

import org.godpro.godpro_server.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  // 모집이 완료되지 않은 프로젝트 목록 조회
  @Query("SELECT p.name AS name, p.shortDescription AS shortDescription,p.front AS front, p.back AS back, p.pm AS pm, p.design AS design FROM Project p")
  List<ProjectRetrieve> findByIsRecruitedFalse();
}
