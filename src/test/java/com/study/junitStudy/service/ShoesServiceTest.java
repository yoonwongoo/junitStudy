package com.study.junitStudy.service;

import static org.assertj.core.api.Assertions.*;
import com.study.junitStudy.dto.ShoesReqDto;
import com.study.junitStudy.dto.ShoesResDto;
import com.study.junitStudy.repository.Shoes;
import com.study.junitStudy.repository.ShoesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ShoesServiceTest {

    @InjectMocks
    private ShoesService shoesService;
    @Mock
    private ShoesRepository shoesRepository;



    @Test
    public void 신발등록(){

        //given
        int shoesPrice =300000;
        String shoseBrandName = "아식스";
        String shoesName = "젤카이노";
        int shoseSize = 280;
        ShoesReqDto shoesReqDto = ShoesReqDto.builder()
                .shoesPrice(shoesPrice)
                .shoesBrandName(shoseBrandName)
                .shoesName(shoesName)
                .shoesSize(shoseSize)
                .build();
        //stub
        when(shoesRepository.save(any())).thenReturn(shoesReqDto.toEntity());

        //when
        ShoesResDto shoesResDto = shoesService.신발등록(shoesReqDto);

        //then
        assertThat(shoesResDto.getShoesSize()).isEqualTo(shoesReqDto.getShoesSize());
        assertThat(shoesResDto.getShoesName()).isEqualTo(shoesReqDto.getShoesName());
    }

    @Test
    public void 신발목록조회(){
        //given 받을 것이 없다


        //stub
        List<Shoes> shoes =
                Arrays.asList(new Shoes(1, "뉴발란스","992",280, 400000,2008),
                        new Shoes(2, "나이키","조던1",230, 300000,20022));

        when(shoesRepository.findAll()).thenReturn(shoes);

        //when
       List<ShoesResDto> shoesList = shoesService.신발목록조회();
        System.out.println(shoesList.get(0).getShoesSize());
        //then
        assertThat(shoesList.get(0).getShoesName()).isEqualTo(shoes.get(0).getShoesName());
        assertThat(shoesList.get(1).getShoesName()).isEqualTo(shoes.get(1).getShoesName());
    }

    @Test
    public void 신발한건조회(){
        //given
        int shoseId =1;


        //stub
        String shoseName ="조던3";
        int shoseSize = 280;
        Shoes shoes = Shoes.builder()
                .id(shoseId)
                .shoesName(shoseName)
                .shoesSize(shoseSize)
                .build();
        Optional<Shoes> shoesOp = Optional.of(shoes);
        when(shoesRepository.findById(shoes.getId())).thenReturn(shoesOp);

        //when

        ShoesResDto shoesOne= shoesService.신발한건조회(shoseId);

        //then
        assertThat(shoes.getShoesSize()).isEqualTo(shoesOne.getShoesSize());
        assertThat(shoes.getShoesName()).isEqualTo(shoesOne.getShoesName());
    }

    @Test
    public void 신발수정(){
        //given
        int shoseId =1;
        ShoesReqDto shoesReqDto = ShoesReqDto.builder()
                .shoesName("젤카이노")
                .shoesSize(230)
                .build();

        //stub

        Shoes shoes = Shoes.builder()
                .id(1)
                .shoesName("조던3")
                .shoesSize(280)
                .build();
        Optional<Shoes> shoesOp = Optional.of(shoes);
        when(shoesRepository.findById(shoes.getId())).thenReturn(shoesOp);

        //when

        ShoesResDto shoesEntity = shoesService.신발수정하기(shoesReqDto,shoseId);
        //then

        assertThat(shoesEntity.getShoesName()).isEqualTo(shoesReqDto.getShoesName());
        assertThat(shoesEntity.getShoesSize()).isEqualTo(shoesReqDto.getShoesSize());
    }
}