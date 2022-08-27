package com.study.junitStudy.dto;

import com.study.junitStudy.repository.Shoes;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class ShoesResDto {
    private String shoesBrandName;

    private String shoesName;

    private int shoesSize;

    private int shoesPrice;

    public ShoesResDto() {
    }

    public ShoesResDto toDto(Shoes shoes){
        this.shoesBrandName= shoes.getShoesBrandName();
        this.shoesName=shoes.getShoesName();
        this.shoesPrice=shoes.getShoesPrice();
        this.shoesSize=shoes.getShoesSize();
        return this;

    }
}
