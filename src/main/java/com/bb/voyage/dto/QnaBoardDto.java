package com.bb.voyage.dto;

import lombok.Data;

@Data
public class QnaBoardDto {
    private int qnaNo;
    private int qnaWriterNo; // 세션에서 받아온 멤버 int no를 입력. 이걸 이용해 nickName을 출력하거나, PW확인 실시
    private String qnaTitle;
    private String qnaContents;
    private String qnaRegDate;
    private int qnaAvailable;
    private int qnaReGroup;
    private int qnaReLevel;
    private int qnaReStep;
    private int qnaHit;
    private int qnaNum; // ROWNUM 용 property
    private String qnaWriterNickname; // DB에 안들어감

}
