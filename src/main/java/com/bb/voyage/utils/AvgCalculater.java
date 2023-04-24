package com.bb.voyage.utils;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReviewDto;
import lombok.Data;

@Data
public class AvgCalculater {
    public ReviewDto getReviewAVG(ReviewDto reviewDto){
    double ratingAVG = Math.round(((reviewDto.getRatingCE()+reviewDto.getRatingFA()+reviewDto.getRatingGS())/3.0)*10)/10.0;
    reviewDto.setRatingAVG(ratingAVG);
    reviewDto.setRatingAVGTxt(""+ratingAVG);
    return reviewDto;
    }
    public PkgDto getPkgAVG(ReviewDto reviewDto, PkgDto pkgDto){
        double ratedGS = (double)((pkgDto.getRatedGS())*(pkgDto.getRatedCount())+reviewDto.getRatingGS())/(pkgDto.getRatedCount()+1);
        double ratedFA = (double)((pkgDto.getRatedFA())*(pkgDto.getRatedCount())+reviewDto.getRatingFA())/(pkgDto.getRatedCount()+1);
        double ratedCE = (double)((pkgDto.getRatedCE())*(pkgDto.getRatedCount())+reviewDto.getRatingCE())/(pkgDto.getRatedCount()+1);
        int ratedCount = (pkgDto.getRatedCount()+1);
        double ratedAvg = Math.round(((ratedGS+ratedFA+ratedCE)/3.0)*10)/10.0;
        pkgDto.setRatedAVG(ratedAvg);
        pkgDto.setRatedAVGTxt(""+ratedAvg);
        pkgDto.setRatedStar(Math.round(pkgDto.getRatedAVG()));
        pkgDto.setRatedGS(ratedGS);
        pkgDto.setRatedFA(ratedFA);
        pkgDto.setRatedCE(ratedCE);
        pkgDto.setRatedCount(ratedCount);
        return pkgDto;
    }
}
