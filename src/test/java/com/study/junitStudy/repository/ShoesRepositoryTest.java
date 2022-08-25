package com.study.junitStudy.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/* test 해야할 것
    신발 등록 shoesSave()
    신발 전체조회 shoesSelectList()
    신발 한 건 조회 shoesSelectOne()
    신발 삭제
    신발 수정
*
* */
@DataJpaTest
class ShoesRepositoryTest {

    @Autowired
    private ShoesRepository shoesRepository;


    @BeforeEach
    public void  신발테스트데이터준비(){
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
        shoesRepository.save(shoes);


    }

    @Order(1)
    @Test
    @DisplayName("신발등록이 되는지 확인")
    public void 신발등록(){
        //given
        String shoesName ="뉴발992";
        int shoesSize = 250;
        String shoesBrandName="뉴발란스";
        int shoesPrice =400000;


        Shoes shoes = Shoes.builder()
                .shoesSize(shoesSize)
                .shoesBrandName(shoesBrandName)
                .shoesPrice(shoesPrice)
                .shoesName(shoesName)
                .build();

        //when
        Shoes shoesEntity=shoesRepository.save(shoes);


        //then
        assertEquals(400000,shoesEntity.getShoesPrice());
        assertEquals("뉴발992",shoesEntity.getShoesName());
        assertEquals(250,shoesEntity.getShoesSize());
        assertEquals("뉴발란스",shoesEntity.getShoesBrandName());


    }
    @Order(2)
    @Test
    public void 신발리스트(){
        //given

        String shoesName ="조던";
        int shoesSize = 270;
        String shoesBrandName="나이키";
        int shoesPrice =200000;


        //when

        List<Shoes> shoesList = shoesRepository.findAll();

        //then

        assertEquals(shoesSize, shoesList.get(0).getShoesSize());
        assertEquals(shoesName, shoesList.get(0).getShoesName());
        assertEquals(shoesBrandName, shoesList.get(0).getShoesBrandName());
        assertEquals(shoesPrice, shoesList.get(0).getShoesPrice());


    }
    @Sql("classpath:database/shoseInit.sql")
    @Order(3)
    @Test
    public void 신발하나조회(){

        //given
        String shoesName ="조던";
        int shoesSize = 270;
        String shoesBrandName="나이키";
        int shoesPrice =200000;


        //when

        Shoes shoesEntity = shoesRepository.findById(1).get();


        //then

        assertEquals(shoesSize, shoesEntity.getShoesSize());
        assertEquals(shoesName, shoesEntity.getShoesName());
        assertEquals(shoesBrandName, shoesEntity.getShoesBrandName());
        assertEquals(shoesPrice, shoesEntity.getShoesPrice());


    }
    @Sql("classpath:database/shoseInit.sql")
    @Order(4)
    @Test
    public void 신발삭제(){

        //given
        int shoesId =1;

        //when
        shoesRepository.deleteById(shoesId);

        //then
        Optional<Shoes> shoesEntity = shoesRepository.findById(shoesId);
        assertFalse(shoesEntity.isPresent());

    }
    @Sql("classpath:database/shoseInit.sql")
    @Order(5)
    @Test
    public void 신발수정(){
        //given
        String shoesName ="테일윈드";//->원래는 나이키
        int shoesSize = 270;
        String shoesBrandName="나이키";
        int shoesPrice =200000;

        Shoes ShoesEntity = Shoes.builder()
                .id(1)
                .shoesName(shoesName)
                .shoesSize(shoesSize)
                .shoesPrice(shoesPrice)
                .shoesBrandName(shoesBrandName)
                .build();
        //when
        shoesRepository.save(ShoesEntity);

        //then

        assertEquals(shoesName,ShoesEntity.getShoesName());
        assertEquals(shoesSize,ShoesEntity.getShoesSize());
        assertEquals(shoesBrandName,ShoesEntity.getShoesBrandName());
        assertEquals(shoesPrice,ShoesEntity.getShoesPrice());

    }


}