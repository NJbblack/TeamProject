package com.bb.voyage.dao;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.bb.voyage.dto.FaqBoardDto;
import com.bb.voyage.dto.FaqCommentDto;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.NoticeBoardDto;
import com.bb.voyage.dto.QnaBoardDto;
import com.bb.voyage.dto.QnaCommentDto;

@Mapper
public interface BoardDao {
  // =================FaqBoard 관련
  List<FaqBoardDto> getAllFaq(HashMap<String, Object> hashMap);    
  FaqBoardDto getOneFaq(int no);
  MemberDto getMemberInfo(int no);
  int totalCountFaq();

  int updateFaqHit(int no);
  int faqBoardWriteProcess(FaqBoardDto faqBoardDto);
  int faqBoardModifyProcess(FaqBoardDto faqBoardDto);
  int faqBoardDeleteProcess(HashMap<String, Object> hashMap);



  // =================NoticeBoard 관련
  List<NoticeBoardDto> getAllNotice(HashMap<String, Object> hashMap);    
  NoticeBoardDto getOneNotice(int no);
  int totalCountNotice();

  int updateNoticeHit(int no);
  int noticeBoardWriteProcess(NoticeBoardDto noticeBoardDto);
  int noticeBoardModifyProcess(NoticeBoardDto noticeBoardDto);
  int noticeBoardDeleteProcess(HashMap<String, Object> hashMap);



  // =================QnaBoard 관련
  List<QnaBoardDto> getAllQna(HashMap<String, Object> hashMap);    
  QnaBoardDto getOneQna(int no);
  int totalCountQna();

  int updateQnaHit(int no);
  int qnaBoardWriteProcess(QnaBoardDto qnaBoardDto);
  int qnaBoardModifyProcess(QnaBoardDto qnaBoardDto);
  int qnaBoardDeleteProcess(HashMap<String, Object> hashMap);


  
  // =================Faq/Qna 댓글 관련
  int insertFaqComment(FaqCommentDto faqCommentDto);
  List<FaqCommentDto> getAllFaqComment(int faqId);
  List<QnaCommentDto> getAllQnaComment(int qnaId);
  int insertQnaComment(QnaCommentDto qnaCommentDto);
}

