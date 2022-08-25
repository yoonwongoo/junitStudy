package com.study.junitStudy.dto;

import com.study.junitStudy.repository.Shoes;

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
