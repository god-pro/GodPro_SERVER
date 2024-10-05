package org.godpro.godpro_server.domain.application.dao;

public interface ApplicationDetail {
    // 유저 프로필 정보
    String getUserName();
    String getUserProfileImageUrl();
    String getUserPortfolioUrl();
    String getUserGithubUrl();
    String getUserIntroduction();

    // 지원 정보
    String getPart();
    String getMotivation();
    String getContact();
}
