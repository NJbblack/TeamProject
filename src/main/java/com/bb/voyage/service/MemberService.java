package com.bb.voyage.service;

import com.bb.voyage.dto.MemberDto;

public interface MemberService {
  // =================Member 관련
  public MemberDto getMemberInfo(int no);
  public int memberJoinProcess(MemberDto memberDto);
  public MemberDto memberLoginProcess(MemberDto memberDto);
  public int memberModifyProcess (MemberDto memberDto);
  public int memberDeleteProcess (MemberDto memberDto);
  public int idCheck (MemberDto memberDto);
  public int updateUserAlert(int userNo, int userAlert);
  public int clearUserAlert(int userNo);
  public int updateAdminAlert(Integer userAlert);
  public MemberDto getMemberByReservNo(Integer reservNo);

}
