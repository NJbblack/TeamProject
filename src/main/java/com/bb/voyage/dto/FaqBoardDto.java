package com.bb.voyage.dto;

import lombok.Data;

@Data
public class FaqBoardDto {
    private int faqNo;
    private int faqWriterNo; // 세션에서 받아온 멤버 int no를 입력. 이걸 이용해 nickName을 출력하거나, PW확인 실시
    private String faqTitle;
    private String faqContents;
    private String faqRegDate;
    private int faqAvailable;
    private int faqReGroup;
    private int faqReLevel;
    private int faqReStep;
    private int faqHit;
    private int faqNum; // ROWNUM 용 property
    private String faqWriterNickname; // DB에 안들어감
}
