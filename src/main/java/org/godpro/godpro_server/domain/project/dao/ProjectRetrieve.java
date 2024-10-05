package org.godpro.godpro_server.domain.project.dao;


public interface ProjectRetrieve {
  String getName();               // 프로젝트 이름
  String getShortDescription();   // 프로젝트 한 줄 소개
  String getFront();                 // 프론트엔드 모집 인원
  String getBack();                  // 백엔드 모집 인원
  String getPm();                    // 기획자 모집 인원
  String getDesign();                // 디자이너 모집 인원
}
