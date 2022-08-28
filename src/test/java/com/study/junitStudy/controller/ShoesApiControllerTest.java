package com.study.junitStudy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.junitStudy.dto.ShoesReqDto;
import com.study.junitStudy.dto.ShoesResDto;
import com.study.junitStudy.repository.Shoes;
import com.study.junitStudy.service.ShoesService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WebMvcTest //controller,filter, advice들만 ioc에 등록
class ShoesApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoesService shoesService;


    @Test
    public void 신발저장() throws Exception {
        //given
        ShoesReqDto shoesReqDto = new ShoesReqDto().builder()
                .shoesBrandName("나이키")
                .shoesSize(290)
                .build();

        String shoesInfo = new ObjectMapper().writeValueAsString(shoesReqDto);
        log.info(shoesInfo);

        //stub
        when(shoesService.addShoes(any())).thenReturn(new ShoesResDto().toDto(shoesReqDto.toEntity()));


        //when
        ResultActions ras = mockMvc.perform(post("/shoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shoesInfo)
                .accept(MediaType.APPLICATION_JSON))


        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.shoesBrandName").value("나이키"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void 신발목록조회() throws Exception {

        //given

        //stub
        List<ShoesResDto> shoesResDtoList = new ArrayList<>();
        shoesResDtoList.add(0,new ShoesResDto().toDto(Shoes.builder()
                .shoesBrandName("나이키")
                .shoesSize(200)
                .build()));

        when(shoesService.findShoesList()).thenReturn(shoesResDtoList);

        //when
        mockMvc.perform(get("/shoes")
                .accept(MediaType.APPLICATION_JSON))

        //then

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].shoesBrandName").value("나이키"))
                .andExpect(jsonPath("$.[0].shoesSize").value(200))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void 신발한건조회() throws Exception {
        //given
        int shoesId =1;

        //stub
        ShoesResDto shoesResDto = new ShoesResDto().toDto(Shoes.builder()
                .shoesName("이지부스트")
                .shoesBrandName("아디다스")
                .build());
        when(shoesService.findShoesOne(shoesId)).thenReturn(shoesResDto);


        //when

        mockMvc.perform(get("/shoes/{shoesId}",shoesId)
                .accept(MediaType.APPLICATION_JSON))

        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shoesName",is(shoesResDto.getShoesName())))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void 신발수정() throws Exception {
        //given
        int shoesId = 1;
        ShoesReqDto shoesReqDto = ShoesReqDto.builder()
                .shoesBrandName("조던 1")
                .shoesName("브레드 토")
                .build();



        //stub
        ShoesResDto shoesResDto = new ShoesResDto().toDto(Shoes.builder()
                .shoesBrandName("조던 1")
                .shoesName("브레드 토")
                .build());
        
        String shoesResDtoInfo = new ObjectMapper().writeValueAsString(shoesResDto);


        when(shoesService.modifyShoesOne(any(),anyInt())).thenReturn(shoesResDto);


        //when

        mockMvc.perform(put("/shoes/{shoesId}",shoesId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(shoesResDtoInfo)
            .accept(MediaType.APPLICATION_JSON))

        //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.shoesBrandName").value("조던 1"))
            .andExpect(jsonPath("$.shoesName").value("브레드 토"))
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void 신발삭제() throws Exception {
        //given
        int shoesId=1;

        //stub
        when(shoesService.removeShoesOne(anyInt())).thenReturn("1");

        //when
        mockMvc.perform(delete("/shoes/{shoesId}",shoesId)
        .accept(MediaType.APPLICATION_JSON))


        //then
        .andExpect(jsonPath("$").value("1"))
        .andDo(MockMvcResultHandlers.print());

    }


}