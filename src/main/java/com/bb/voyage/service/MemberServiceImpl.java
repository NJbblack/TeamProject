package com.bb.voyage.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bb.voyage.dao.MemberDao;
import com.bb.voyage.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  MemberDao memberDao;
  @Value("${file.path}")
  private String uploadFolder;

/////////////////////////////////////////////////////////////////////
////Member 관련 서비스
  public MemberDto getMemberInfo(int no) {
    return memberDao.getMemberInfo(no);
  }

  public int memberJoinProcess(MemberDto memberDto) {
    int result = memberDao.memberJoinProcess(memberDto);
    return result;
  }

  public MemberDto memberLoginProcess(MemberDto memberDto) {
    MemberDto loginMemberDto = memberDao.memberLoginProcess(memberDto);
    return loginMemberDto;
  }

  public int memberModifyProcess(MemberDto memberDto) {
    int result = memberDao.memberModifyProcess(memberDto);
        return result;
  }

  public int memberDeleteProcess(MemberDto memberDto) {
    int result = memberDao.memberDeleteProcess(memberDto);
    return result;
  }

  public int idCheck(MemberDto memberDto) {
    return memberDao.idCheck(memberDto);
  }

  public int updateUserAlert(int userNo, int userAlert) {
    HashMap<String,Integer> hashMap = new HashMap<>();
    hashMap.put("userNo", userNo);
    hashMap.put("userAlert", userAlert);
    int result = memberDao.updateUserAlert(hashMap);
    return result;
  }

  public int clearUserAlert(int userNo) {
    int result = updateUserAlert(userNo, 0);
    return result;
  }

  public int updateAdminAlert(Integer userAlert) {
    int result = memberDao.updateAdminAlert(userAlert);
    return result;
  }

  public MemberDto getMemberByReservNo(Integer reservNo) {
    return memberDao.getMemberByReservNo(reservNo);
  }
  
  
  
}
