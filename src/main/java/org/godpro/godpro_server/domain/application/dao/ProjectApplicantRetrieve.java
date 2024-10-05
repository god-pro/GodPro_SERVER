package org.godpro.godpro_server.domain.application.dao;

public interface ProjectApplicantRetrieve {

    // 유저 정보
    String getUserName();
    String getUserProfileImageUrl();

    // 지원 정보
    Long getApplicationId();
    String getPart();
}
