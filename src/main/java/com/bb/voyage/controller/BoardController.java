package com.bb.voyage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bb.voyage.dto.FaqBoardDto;
import com.bb.voyage.dto.FaqCommentDto;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.NoticeBoardDto;
import com.bb.voyage.dto.QnaBoardDto;
import com.bb.voyage.dto.QnaCommentDto;
import com.bb.voyage.service.BoardService;
import com.bb.voyage.service.MemberService;
import com.bb.voyage.utils.HitChecker;
import com.bb.voyage.utils.PagingMaker;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardService boardService;
  @Autowired
  MemberService memberService;

/////////////////////////////////////////////////////////////////////
////FAQ 게시판용 컨트롤러
  @GetMapping("/faqboard")
  public String faqBoard(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
    List<FaqBoardDto> faqList = boardService.getAllFaq(page, searchTxt, category);
    pagingMaker.setTotalCount(boardService.totalCountFaq(), (int)page);
    model.addAttribute("faqList", faqList);
    model.addAttribute("pagingMaker", pagingMaker);
    return "/board/faqboard";
  }

  @GetMapping("/faqboardwrite")
  public String faqBoardWrite(HttpServletRequest request, HttpSession session) {
    MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember");
    if(memberDto.getUserRank()==9){
      return "/board/faqboardwrite";
   } else {
   return null;
   }
  }

  @PostMapping("/faqBoardWriteProcess")
  public String faqBoardWriteProcess(FaqBoardDto faqBoardDto) {
    boardService.faqBoardWriteProcess(faqBoardDto);
    return "redirect:/board/faqboard";
  }

  @GetMapping("/faqboardview")
  public String faqBoardView(Model model, int no, HttpServletRequest request, HttpServletResponse response, HitChecker hitChecker){
    FaqBoardDto faqBoardDto = boardService.getOneFaq(no);
    MemberDto memberDto = memberService.getMemberInfo((int)faqBoardDto.getFaqWriterNo());
    log.info("결과1: {}",faqBoardDto);
    log.info("결과2: {}",memberDto);
    
    model.addAttribute("faqBoardDto", faqBoardDto);
    model.addAttribute("memberDto", memberDto);
    hitChecker.faqHitChecker(no, request, response, boardService);
    return "/board/faqboardview";
  }

  @GetMapping("/faqboardmodify")
  public String faqBoardModify(Model model, int no, HttpServletRequest request, HttpSession session) {
    MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember");
    if(memberDto.getUserRank()==9){
      FaqBoardDto faqBoardDto = boardService.getOneFaq(no);
      model.addAttribute("faqBoardDto", faqBoardDto);
      return "/board/faqboardmodify";
   } else {
   return null;
   }
  }

  @PostMapping("/faqBoardModifyProcess")
  public String faqBoardModifyProcess(FaqBoardDto faqBoardDto,int no) {
    faqBoardDto.setFaqWriterNo(no);
    boardService.faqBoardModifyProcess(faqBoardDto);
    return "redirect:/board/faqboard";
  }

  @PostMapping("/faqBoardDeleteProcess")
  @ResponseBody
  public Map<String, Object> faqBoardDeleteProcess(int faqNo, String userPw) {
    int result = boardService.faqBoardDeleteProcess(faqNo, userPw);
    Map<String, Object> sendJson = new HashMap<>();
    if (result > 0) {
      sendJson.put("msg", "ok");
    } else {
      sendJson.put("msg", "fail");
    }
    return sendJson;
  }


  
/////////////////////////////////////////////////////////////////////
////NOTICE 게시판용 컨트롤러
  @GetMapping("/noticeboard")
  public String noticeBoard(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
    List<NoticeBoardDto> noticeList = boardService.getAllNotice(page, searchTxt, category);
    pagingMaker.setTotalCount(boardService.totalCountNotice(), (int)page);
    model.addAttribute("pagingMaker", pagingMaker);
    model.addAttribute("noticeList", noticeList);
    return "/board/noticeboard";
  }

  @GetMapping("/noticeboardwrite")
  public String noticeBoardWrite(HttpServletRequest request, HttpSession session) {
     MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember");
     if(memberDto.getUserRank()==9){
       return "/board/noticeboardwrite";
    } else {
    return "/board/noticeboard";
    }
  }

  @PostMapping("/noticeBoardWriteProcess")
  public String noticeBoardWriteProcess(NoticeBoardDto noticeBoardDto,int no) {
    noticeBoardDto.setNoticeWriterNo(no);
    boardService.noticeBoardWriteProcess(noticeBoardDto);
    return "redirect:/board/noticeboard";
  }

  @GetMapping("/noticeboardview")
  public String noticeBoardView(Model model, int no, HttpServletRequest request, HttpServletResponse response, HitChecker hitChecker){
    NoticeBoardDto noticeBoardDto = boardService.getOneNotice(no);
    MemberDto memberDto = memberService.getMemberInfo((int)noticeBoardDto.getNoticeWriterNo());
    model.addAttribute("noticeBoardDto", noticeBoardDto);
    model.addAttribute("memberDto", memberDto);
    hitChecker.noticeHitChecker(no, request, response, boardService);
    return "/board/noticeboardview";
  }

  @GetMapping("/noticeboardmodify")
  public String noticeBoardModify(Model model, int no,  HttpServletRequest request, HttpSession session) {
     MemberDto memberDto02 = (MemberDto)session.getAttribute("loggedMember");
    if(memberDto02.getUserRank()==9){
    NoticeBoardDto noticeBoardDto = boardService.getOneNotice(no);
    model.addAttribute("noticeBoardDto", noticeBoardDto);
    return "/board/noticeboardmodify";
    } else {
      return null;
    }
  }

  @PostMapping("/noticeBoardModifyProcess")
  public String noticeBoardModifyProcess(NoticeBoardDto noticeBoardDto, int no) {
    noticeBoardDto.setNoticeWriterNo(no);
    boardService.noticeBoardModifyProcess(noticeBoardDto);
    return "redirect:/board/noticeboard";
  }
  
  @PostMapping("/noticeBoardDeleteProcess")
  @ResponseBody
  public Map<String, Object> noticeBoardDeleteProcess(int noticeNo, String userPw) {
    int result = boardService.noticeBoardDeleteProcess(noticeNo, userPw);
    Map<String, Object> sendJson = new HashMap<>();
    if (result > 0) {
      sendJson.put("msg", "ok");
    } else {
      sendJson.put("msg", "fail");
    }
    return sendJson;
  }



/////////////////////////////////////////////////////////////////////
////Faq/Notice 댓글 관련 컨트롤러
@PostMapping("/getAllFaqComment")
@ResponseBody
public List<FaqCommentDto>  getAllFaqComment(int faqId){
    List<FaqCommentDto> faqCommentList = boardService.getAllFaqComment(faqId);
    return faqCommentList;
}

@PostMapping("/insertFaqComment")
@ResponseBody
public List<FaqCommentDto> insertFaqComment(FaqCommentDto faqCommentDto, HttpServletRequest request,  HttpServletResponse response){
    int result = boardService.insertFaqComment(faqCommentDto, request, response);
    List<FaqCommentDto> faqCommentList = boardService.getAllFaqComment(faqCommentDto.getFaqId());
    if (result>0) {
        return faqCommentList;
    } else {
        return faqCommentList;
    }
}



/////////////////////////////////////////////////////////////////////
////QNA 게시판용 컨트롤러
@GetMapping("/qnaboard")
public String qnaBoard(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
  List<QnaBoardDto> qnaList = boardService.getAllQna(page, searchTxt, category);
  pagingMaker.setTotalCount(boardService.totalCountQna(), (int)page);
  model.addAttribute("qnaList", qnaList);
  model.addAttribute("pagingMaker", pagingMaker);
  return "/board/qnaboard";
}

@GetMapping("/qnaboardwrite") // 질문
public String qnaBoardWrite(HttpServletRequest request, HttpSession session) {
    return "/board/qnaboardwrite";
}

@PostMapping("/qnaBoardWriteProcess")
public String faqBoardWriteProcess(QnaBoardDto qnaBoardDto) {
  boardService.qnaBoardWriteProcess(qnaBoardDto);
  return "redirect:/board/qnaboard";
}

@GetMapping("/qnaboardview")
public String qnaBoardView(Model model, int no, HttpServletRequest request, HttpServletResponse response, HitChecker hitChecker){
  QnaBoardDto qnaBoardDto = boardService.getOneQna(no);
  MemberDto memberDto = memberService.getMemberInfo((int)qnaBoardDto.getQnaWriterNo());
  model.addAttribute("qnaBoardDto", qnaBoardDto);
  model.addAttribute("memberDto", memberDto);
  hitChecker.qnaHitChecker(no, request, response, boardService);
  return "/board/qnaboardview";
}

@GetMapping("/qnaboardmodify")
public String qnaBoardModify(Model model, int no, HttpServletRequest request, HttpSession session) {
  MemberDto memberDto = (MemberDto)session.getAttribute("loggedMember");
     if(memberDto.getUserRank()>=0){
    QnaBoardDto qnaBoardDto = boardService.getOneQna(no);
    model.addAttribute("qnaBoardDto", qnaBoardDto);
    return "/board/qnaboardmodify";
 } else {
 return null;
 }
}

@PostMapping("/qnaBoardModifyProcess")
public String qnaBoardModifyProcess(QnaBoardDto qnaBoardDto,int no) {
  qnaBoardDto.setQnaWriterNo(no);
  boardService.qnaBoardModifyProcess(qnaBoardDto);
  return "redirect:/board/qnaboard";
}

@PostMapping("/qnaBoardDeleteProcess")
@ResponseBody
public Map<String, Object> qnaBoardDeleteProcess(int qnaNo, String userPw) {
  int result = boardService.qnaBoardDeleteProcess(qnaNo, userPw);
  Map<String, Object> sendJson = new HashMap<>();
  if (result > 0) {
    sendJson.put("msg", "ok");
  } else {
    sendJson.put("msg", "fail");
  }
  return sendJson;
}

@PostMapping("/getAllQnaComment")
@ResponseBody
public List<QnaCommentDto>  getAllQnaComment(int qnaId){
  List<QnaCommentDto> qnaCommentList = boardService.getAllQnaComment(qnaId);
  return qnaCommentList;
}

@PostMapping("/insertQnaComment")
@ResponseBody
public List<QnaCommentDto> insertQnaComment(QnaCommentDto qnaCommentDto, HttpServletRequest request,  HttpServletResponse response){
  int result = boardService.insertQnaComment(qnaCommentDto, request, response);
  List<QnaCommentDto> qnaCommentList = boardService.getAllQnaComment(qnaCommentDto.getQnaId());
  if (result>0) {
      return qnaCommentList;
  } else {
      return qnaCommentList;
  }
}
}