package com.bb.voyage.dto;

import lombok.Data;

@Data
public class QnaCommentDto {
    private int no;
    private int qnaId;
    private String comments;
    private String commenter;
}
