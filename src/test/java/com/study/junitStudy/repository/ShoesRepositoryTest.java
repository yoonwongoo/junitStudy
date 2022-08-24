package com.study.junitStudy.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/* test 해야할 것
    신발 등록
    신발 전체조회
    신발 한 건 조회
    신발 삭제
    신발 수정
*
* */
@DataJpaTest
class ShoesRepositoryTest {

    @Autowired
    private ShoesRepository shoesRepository;

    @Test
    public void ShoesSave(){
        //given
        String shoesName ="조던";
        int shoesSize = 270;
        String shoesBrandName="나이키";
        int shoesPrice =200000;
        Shoes shoes = Shoes.builder()
                .shoesName(shoesName)
                .shoesSize(shoesSize)
                .shoesBrandName(shoesBrandName)
                .shoesPrice(shoesPrice)
                .build();

        //when
        Shoes shoesEntity=shoesRepository.save(shoes);


        //then
        assertEquals("조던",shoesEntity.getShoesName());
        assertEquals(270,shoesEntity.getShoesSize());
        assertEquals("나이키",shoesEntity.getShoesBrandName());
        assertEquals(200000,shoesEntity.getShoesPrice());

    }


}