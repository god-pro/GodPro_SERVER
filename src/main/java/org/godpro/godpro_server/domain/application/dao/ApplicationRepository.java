package org.godpro.godpro_server.domain.application.dao;

import org.godpro.godpro_server.domain.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // 특정 프로젝트에서 특정 Application 정보를 단건 조회
    @Query("SELECT " +
            "u.name AS userName, u.profileImageUrl AS userProfileImageUrl, u.portfolioUrl AS userPortfolioUrl, u.githubUrl AS userGithubUrl, u.introduction AS userIntroduction, " +
            "a.part AS part, a.motivation AS motivation, a.contact AS contact " +
            "FROM Application a " +
            "JOIN a.user u " +
            "JOIN a.project p " +
            "WHERE a.id = :applicationId AND p.id = :projectId")
    ApplicationDetail findApplicationDetail(@Param("projectId") Long projectId, @Param("applicationId") Long applicationId);

    // 특정 프로젝트에 지원한 모든 지원자의 정보 (Application ID, 이름, 프로필 사진, 지원 파트)
    @Query("SELECT a.id AS applicationId, a.part AS part, " +
            "u.name AS userName, u.profileImageUrl AS userProfileImageUrl " +
            "FROM Application a " +
            "JOIN a.user u " +
            "JOIN a.project p " +
            "WHERE p.id = :projectId")
    List<ProjectApplicantRetrieve> findApplicantsByProjectId(@Param("projectId") Long projectId);
}
