package org.godpro.godpro_server.domain.project.dao;


public interface ProjectRetrieve {
  String getName();               // 프로젝트 이름
  String getShortDescription();   // 프로젝트 한 줄 소개
  int getFront();                 // 프론트엔드 모집 인원
  int getBack();                  // 백엔드 모집 인원
  int getPm();                    // 기획자 모집 인원
  int getDesign();                // 디자이너 모집 인원
  int getTotalRecruitment();
  int getAcceptedApplicantCount();
}
