package org.godpro.godpro_server.domain.project.dao;

import java.time.LocalDateTime;

public interface ReceiveProject {
    //creater의 아이디
    Long getCreator();

    //프로젝트 이름
    String getName();

    //프로젝트 한줄 소개
    String getShortDescription();

    //자세한 프로젝트 개요
    String getDetailDescription();

    //예상 개발 기간
    int getEta();

    //마감 날짜
    LocalDateTime getRecruitmentEndDate();

    //백앤드 인원
    int getBack();

    //프론트 앤드 인원
    int getFront();

    //기획 인원
    int getPm();

    //디자인 인원
    int getDesign();

    //모집 완료
    boolean getIsRecruited();
}
