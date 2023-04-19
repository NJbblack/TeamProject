package com.bb.voyage.dto;

import lombok.Data;

@Data
public class NoticeBoardDto {
    private int noticeNo;
    private int noticeWriterNo; // 세션에서 받아온 멤버 int no를 입력. 이걸 이용해 nickName을 출력하거나, PW확인 실시
    private String noticeTitle;
    private String noticeContents;
    private String noticeRegDate;
    private int noticeAvailable;
    private int noticeReGroup;
    private int noticeReLevel;
    private int noticeReStep;
    private int noticeHit;
    private int noticeNum; // ROWNUM 용 property
    private String noticeWriterNickname; // DB에 안들어감

}
