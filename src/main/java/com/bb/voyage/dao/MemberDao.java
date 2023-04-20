package com.bb.voyage.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.bb.voyage.dto.MemberDto;

@Mapper
public interface MemberDao {

  // =================Member 관련
  public MemberDto getMemberInfo(int no);
  public int idCheck (MemberDto memberDto);

  public int memberJoinProcess(MemberDto memberDto);
  public MemberDto memberLoginProcess(MemberDto memberDto);
  public int memberModifyProcess (MemberDto memberDto);
  public int memberDeleteProcess (MemberDto memberDto);
  public int updateUserAlert(HashMap<String,Integer> hashMap);
  public int updateAdminAlert(Integer userAlert);

  public MemberDto getMemberByReservNo(Integer reservNo);

}
