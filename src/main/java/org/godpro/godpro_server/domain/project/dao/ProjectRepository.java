package org.godpro.godpro_server.domain.project.dao;

import org.godpro.godpro_server.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // 모집이 완료되지 않은 프로젝트 목록 조회
    @Query("SELECT p.name AS name, p.shortDescription AS shortDescription,p.front AS front, p.back AS back, p.pm AS pm, p.design AS design FROM Project p")
    List<ProjectRetrieve> findByIsRecruitedFalse();

    // 모집 마감 시간이 지났으나 모집 완료 처리하지 않은 프로젝트 목록 조회
    @Query("SELECT p FROM Project p WHERE p.recruitmentEndDate <= :now AND p.isRecruited = false")
    List<Project> findAllByRecruitmentEndDatePassed(LocalDateTime now);

    //프로젝트 단건 조회
    @Query("SELECT p.creator.id AS creator, p.name AS name, p.shortDescription AS shortDescription, p.detailDescription AS detailDescription, p.eta AS eta, p.recruitmentEndDate AS recruitmentEndDate, p.back AS back, p.front AS front, p.pm AS pm, p.design AS design, p.isRecruited AS isRecruited FROM Project p WHERE p.id = :id")
    Optional<ReceiveProject> findProjectById(@Param("id") Long id);
}
