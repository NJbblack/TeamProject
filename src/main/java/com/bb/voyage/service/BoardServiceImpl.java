package com.bb.voyage.service;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bb.voyage.dao.BoardDao;
import com.bb.voyage.dao.MemberDao;
import com.bb.voyage.dto.FaqBoardDto;
import com.bb.voyage.dto.FaqCommentDto;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.NoticeBoardDto;
import com.bb.voyage.dto.QnaBoardDto;
import com.bb.voyage.dto.QnaCommentDto;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberDao memberDao;

/////////////////////////////////////////////////////////////////////
////FaqBoard 관련 서비스
    public List<FaqBoardDto> getAllFaq(int page, String searchTxt, String category) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("page", page);
        hashMap.put("searchTxt", searchTxt);
        hashMap.put("category", category);
        List<FaqBoardDto> faqList = boardDao.getAllFaq(hashMap);
        for(FaqBoardDto item: faqList){
            item.setFaqWriterNickname(memberDao.getMemberInfo(item.getFaqWriterNo()).getUserNickname());
        }
        return faqList;
    }

    public FaqBoardDto getOneFaq(int no) {
        return boardDao.getOneFaq(no);
    }

    public MemberDto getMemberInfo(int no) {
        return boardDao.getMemberInfo(no);
    }

    public int totalCountFaq() {
        return boardDao.totalCountFaq();
    }

    public int updateFaqHit(int no) {
        return boardDao.updateFaqHit(no);
    }

    public int faqBoardWriteProcess(FaqBoardDto faqBoardDto) {
        return boardDao.faqBoardWriteProcess(faqBoardDto);
    }

    public int faqBoardModifyProcess(FaqBoardDto faqBoardDto) {
        return boardDao.faqBoardModifyProcess(faqBoardDto);
    }

    public int faqBoardDeleteProcess(int faqNo, String userPw) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("faqNo", faqNo);
        hashMap.put("userPw", userPw);
        return boardDao.faqBoardDeleteProcess(hashMap);
    }

/////////////////////////////////////////////////////////////////////
////NoticeBoard 관련 서비스
    public List<NoticeBoardDto> getAllNotice(int page, String searchTxt, String category) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("page", page);
        hashMap.put("searchTxt", searchTxt);
        hashMap.put("category", category);
        List<NoticeBoardDto> noticeList = boardDao.getAllNotice(hashMap);
        for(NoticeBoardDto item: noticeList){
            item.setNoticeWriterNickname(memberDao.getMemberInfo(item.getNoticeWriterNo()).getUserNickname());
        }
        return noticeList;
    }

    public NoticeBoardDto getOneNotice(int no) {
        return boardDao.getOneNotice(no);
    }

    public int totalCountNotice() {
        return boardDao.totalCountNotice();
    }

    public int updateNoticeHit(int no) {
        return boardDao.updateNoticeHit(no);
    }

    public int noticeBoardWriteProcess(NoticeBoardDto noticeBoardDto) {
        return boardDao.noticeBoardWriteProcess(noticeBoardDto);
    }
    
    public int noticeBoardModifyProcess(NoticeBoardDto noticeBoardDto) {
        return boardDao.noticeBoardModifyProcess(noticeBoardDto);
    }

    public int noticeBoardDeleteProcess(int noticeNo, String userPw) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("noticeNo", noticeNo);
        hashMap.put("userPw", userPw);
        return boardDao.noticeBoardDeleteProcess(hashMap);
    }    

/////////////////////////////////////////////////////////////////////
////Faq/Notice 댓글 관련 서비스
    public int insertFaqComment(FaqCommentDto faqCommentDto, HttpServletRequest request,
    HttpServletResponse response) {
        HttpSession session = request.getSession(); // 세션을 받아와서
        MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember"); // 세션 중에 "loggedMember"의 값을 받아온 후, MemberDto로 변환
        faqCommentDto.setCommenter(memberDto.getUserNickname()); // memberDto에서 닉네임을 추출한 뒤, commenter로서 faqCommentDto에 집어넣음
        int result = boardDao.insertFaqComment(faqCommentDto);
        return result;
    }
    
    public List<FaqCommentDto> getAllFaqComment(int faqId) {
        List<FaqCommentDto> faqCommentList = boardDao.getAllFaqComment(faqId);
        return faqCommentList;
    }  

/////////////////////////////////////////////////////////////////////
////QnaBoard 관련 서비스
public List<QnaBoardDto> getAllQna(int page, String searchTxt, String category) {
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put("page", page);
    hashMap.put("searchTxt", searchTxt);
    hashMap.put("category", category);
    List<QnaBoardDto> qnaList = boardDao.getAllQna(hashMap);
    for(QnaBoardDto item: qnaList){
        item.setQnaWriterNickname(memberDao.getMemberInfo(item.getQnaWriterNo()).getUserNickname());
    }
    return qnaList;
}

public QnaBoardDto getOneQna(int no) {
    return boardDao.getOneQna(no);
}

public int totalCountQna() {
    return boardDao.totalCountQna();
}

public int updateQnaHit(int no) {
    return boardDao.updateQnaHit(no);
}

public int qnaBoardWriteProcess(QnaBoardDto qnaBoardDto) {
    return boardDao.qnaBoardWriteProcess(qnaBoardDto);
}

public int qnaBoardModifyProcess(QnaBoardDto qnaBoardDto) {
    return boardDao.qnaBoardModifyProcess(qnaBoardDto);
}

public int qnaBoardDeleteProcess(int qnaNo, String userPw) {
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put("qnaNo", qnaNo);
    hashMap.put("userPw", userPw);
    return boardDao.qnaBoardDeleteProcess(hashMap);
}

public int insertQnaComment(QnaCommentDto qnaCommentDto, HttpServletRequest request,
HttpServletResponse response) {
    HttpSession session = request.getSession(); // 세션을 받아와서
    MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember"); // 세션 중에 "loggedMember"의 값을 받아온 후, MemberDto로 변환
    qnaCommentDto.setCommenter(memberDto.getUserNickname()); // memberDto에서 닉네임을 추출한 뒤, commenter로서 faqCommentDto에 집어넣음
    int result = boardDao.insertQnaComment(qnaCommentDto);
    return result;
}

public List<QnaCommentDto> getAllQnaComment(int qnaId) {
    List<QnaCommentDto> qnaCommentList = boardDao.getAllQnaComment(qnaId);
    return qnaCommentList;
}  

}