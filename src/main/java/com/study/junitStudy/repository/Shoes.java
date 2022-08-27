package com.study.junitStudy.repository;


import com.study.junitStudy.dto.ShoesReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor //jpa에는 entity가 붙은 곳에는 기본생성자가 필요하다
@Getter
@Entity
public class Shoes {

    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    private int id;

    @Column(length = 30, nullable = false)
    private String shoesBrandName;

    @Column(length = 100, nullable = false)
    private String shoesName;

    @Column(length = 30, nullable = false)
    private int shoesSize;

    @Column(length = 30, nullable = false)
    private int shoesPrice;

    @Column(length = 30, nullable = true)
    private int shoesDate;




    @Builder
    public Shoes(int id, String shoesBrandName, String shoesName, int shoesSize, int shoesPrice, int shoesDate) {
        this.id = id;
        this.shoesBrandName = shoesBrandName;
        this.shoesName = shoesName;
        this.shoesSize = shoesSize;
        this.shoesPrice = shoesPrice;
        this.shoesDate = shoesDate;
    }

    public void shoesUpdate(ShoesReqDto shoesReqDto){
        this.shoesBrandName = shoesReqDto.getShoesBrandName();
        this.shoesName = shoesReqDto.getShoesName();
        this.shoesSize = shoesReqDto.getShoesSize();
        this.shoesPrice = shoesReqDto.getShoesPrice();
    }
}
