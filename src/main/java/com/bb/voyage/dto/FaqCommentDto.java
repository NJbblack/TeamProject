package com.bb.voyage.dto;

import lombok.Data;

@Data
public class FaqCommentDto {
    private int no;
    private int faqId;
    private String comments;
    private String commenter;
}
