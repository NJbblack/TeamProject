package com.bb.voyage.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReviewDto;

@Mapper
public interface PkgDao {
    public List<PkgDto> pkgKoreaList();
    public List<PkgDto> pkgJapanList();
    public List<PkgDto> pkgChinaList();
    public List<PkgDto> getDiscountedPkgList();
    public List<PkgDto> getRecommendedPkgList();
    public int pkgSetRating(PkgDto pkgDto);
    public List<ReviewDto> getReview(PkgDto pkgDto);
    public ReviewDto getSelectedReview(int reviewNo);
    public List<PkgDto> getTempRecoList(PkgDto pkgDto);
    public List<PkgDto> getRecommendedList(HashMap hashMap);
}
