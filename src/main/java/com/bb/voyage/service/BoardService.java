package com.bb.voyage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bb.voyage.dto.FaqBoardDto;
import com.bb.voyage.dto.FaqCommentDto;
import com.bb.voyage.dto.NoticeBoardDto;
import com.bb.voyage.dto.QnaBoardDto;
import com.bb.voyage.dto.QnaCommentDto;

public interface BoardService {
  // =================FaqBoard 관련
  List<FaqBoardDto> getAllFaq(int page, String searchTxt, String category);    
  FaqBoardDto getOneFaq(int no);
  int totalCountFaq();
  int updateFaqHit(int no);
  int faqBoardWriteProcess(FaqBoardDto faqBoardDto);
  int faqBoardModifyProcess(FaqBoardDto faqBoardDto);
  int faqBoardDeleteProcess(int faqNo, String userPw);
  
  // =================NoticeBoard 관련
  List<NoticeBoardDto> getAllNotice(int page, String searchTxt, String category);    
  NoticeBoardDto getOneNotice(int no);
  int totalCountNotice();
  int updateNoticeHit(int no);
  int noticeBoardWriteProcess(NoticeBoardDto noticeBoardDto);
  int noticeBoardModifyProcess(NoticeBoardDto noticeBoardDto);
  int noticeBoardDeleteProcess(int noticeNo, String userPw);
  
  // =================Faq/Notice 댓글 관련
  int insertFaqComment(FaqCommentDto faqCommentDto, HttpServletRequest request,  HttpServletResponse response);
  List<FaqCommentDto> getAllFaqComment(int faqId);

  // =================QnaBoard 관련
  List<QnaBoardDto> getAllQna(int page, String searchTxt, String category);    
  QnaBoardDto getOneQna(int no);
  int totalCountQna();
  int updateQnaHit(int no);
  int qnaBoardWriteProcess(QnaBoardDto qnaBoardDto);
  int qnaBoardModifyProcess(QnaBoardDto qnaBoardDto);
  int qnaBoardDeleteProcess(int qnaNo, String userPw);

  int insertQnaComment(QnaCommentDto qnaCommentDto, HttpServletRequest request,  HttpServletResponse response);
  List<QnaCommentDto> getAllQnaComment(int qnaId);
}
