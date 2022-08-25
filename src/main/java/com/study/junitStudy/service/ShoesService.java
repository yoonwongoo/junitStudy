package com.study.junitStudy.service;


import com.study.junitStudy.dto.ShoesReqDto;
import com.study.junitStudy.dto.ShoesResDto;
import com.study.junitStudy.repository.Shoes;
import com.study.junitStudy.repository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShoesService {

    private final ShoesRepository shoesRepository;


    @Transactional(rollbackFor = RuntimeException.class)
    public ShoesResDto 신발등록(ShoesReqDto shoesReqDto){

        Shoes shoesEntity = shoesRepository.save(shoesReqDto.toEntity());
        return new ShoesResDto().toDto(shoesEntity);
    }


    @Transactional(readOnly = true)
    public List<ShoesResDto> 신발목록조회(){
    List<Shoes> shoesList = shoesRepository.findAll();

    return shoesList.stream().map(sL-> new ShoesResDto().toDto(sL)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ShoesResDto 신발한건조회(int id){
        return new ShoesResDto().toDto(shoesRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지않는 아이디입니다")));


    }
    @Transactional(rollbackFor = RuntimeException.class)
    public void 신발삭제하기(int id){

        shoesRepository.deleteById(id);

    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void 신발수정하기(ShoesReqDto dto, int id){
        Shoes shoesEntity = shoesRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디가 없습니다"));
        shoesEntity.shoesUpdate(shoesEntity);
    }
}
