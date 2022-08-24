package com.study.junitStudy.dto;

import com.study.junitStudy.repository.Shoes;

import javax.persistence.Column;
//test시에는 굳이..필요없음. 그냥 바로 객체 생성해서 하자
public class ShoesReqDto {

    private String shoesBrandName;

    private String shoesName;

    private int shoesSize;

    private int shoesPrice;

    public Shoes toEntity(){
        return Shoes.builder()
                .shoesBrandName(shoesBrandName)
                .shoesName(shoesName)
                .shoesSize(shoesSize)
                .shoesPrice(shoesPrice)
                .build();

    }
}
