package com.study.junitStudy.service;


import com.study.junitStudy.dto.ShoesReqDto;
import com.study.junitStudy.dto.ShoesResDto;
import com.study.junitStudy.repository.Shoes;
import com.study.junitStudy.repository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoesService {

    private final ShoesRepository shoesRepository;


    @Transactional(rollbackFor = RuntimeException.class)
    public ShoesResDto addShoes(ShoesReqDto shoesReqDto){

        Shoes shoesEntity = shoesRepository.save(shoesReqDto.toEntity());
        return new ShoesResDto().toDto(shoesEntity);
    }


    @Transactional(readOnly = true)
    public List<ShoesResDto> findShoesList(){
    List<Shoes> shoesList = shoesRepository.findAll();
        shoesList.forEach(s-> System.out.println(s.getShoesName()));
    return shoesList.stream().map(sL-> new ShoesResDto().toDto(sL)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ShoesResDto findShoesOne(int id){
        return new ShoesResDto().toDto(shoesRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지않는 아이디입니다")));


    }
    @Transactional(rollbackFor = RuntimeException.class)
    public void removeShoesOne(int id){

        shoesRepository.deleteById(id);

    }
    //void 타입으로 해도되지만 test를 위해 타입변경.
    @Transactional(rollbackFor = RuntimeException.class)
    public ShoesResDto modifyShoesOne(ShoesReqDto dto, int id){
        Shoes shoesEntity = shoesRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디가 없습니다"));
        shoesEntity.shoesUpdate(dto);
        return new ShoesResDto().toDto(shoesEntity);
    }
}
