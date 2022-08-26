package com.study.junitStudy.dto;

import com.study.junitStudy.repository.Shoes;
import lombok.Builder;
import lombok.Getter;

@Getter
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

    @Builder
    public ShoesReqDto(String shoesBrandName, String shoesName, int shoesSize, int shoesPrice) {
        this.shoesBrandName = shoesBrandName;
        this.shoesName = shoesName;
        this.shoesSize = shoesSize;
        this.shoesPrice = shoesPrice;
    }
}
