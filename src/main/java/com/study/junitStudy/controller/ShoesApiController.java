package com.study.junitStudy.controller;


import com.study.junitStudy.dto.ShoesReqDto;
import com.study.junitStudy.dto.ShoesResDto;
import com.study.junitStudy.service.ShoesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ShoesApiController {

    private final ShoesService shoesService;


    @ApiOperation(value="신발의 정보를 저장", notes = "신발의 정보를 저장합니다.")
    @PostMapping("/shoes")
    public ResponseEntity<ShoesResDto> shoesAdd(@RequestBody ShoesReqDto shoesReqDto){
        log.info(shoesReqDto.toString());
       ShoesResDto shoesResDto= shoesService.addShoes(shoesReqDto);
        return new ResponseEntity<ShoesResDto>(shoesResDto,HttpStatus.CREATED);
        }

    @ApiOperation(value="신발정보 리스트 조회",notes = "신발의 정보리스트를 조회합니다")
    @GetMapping("/shoes")
    public ResponseEntity<?> shoesList(){
        List<ShoesResDto> shoesResDtoList =shoesService.findShoesList();
        return new ResponseEntity<>(shoesResDtoList,HttpStatus.OK);
    }

    @ApiOperation(value="신발 하나의 정보를 조회",notes = "신발의 ID값을 이용하여 조회합니다")
    @GetMapping("/shoes/{shoesId}")
    public ResponseEntity<ShoesResDto> shoesDetails(@PathVariable int shoesId){


        return new ResponseEntity<ShoesResDto>(shoesService.findShoesOne(shoesId),HttpStatus.OK);

    }
    @ApiOperation(value="신발의 정보를 수정",notes = "수정할 신발의 정보를 입력받고 신발의 ID값을 이용하여 신발의 정보를 수정합니다")
    @PutMapping("/shoes/{shoesId}")
    public ResponseEntity<ShoesResDto> shoesModify(@RequestBody ShoesReqDto shoesReqDto,
                                                   @PathVariable int shoesId){

        return new ResponseEntity<ShoesResDto>(shoesService.modifyShoesOne(shoesReqDto,shoesId),HttpStatus.OK);
    }
    @ApiOperation(value="신발의 정보를 삭제",notes = "신발의 ID값을 이용하여 신발을 삭제합니다")
    @DeleteMapping("/shoes/{shoesId}")
    public ResponseEntity<String> shoesRemove(@PathVariable int shoesId){
        shoesService.removeShoesOne(shoesId);
        return new ResponseEntity<String>(shoesService.removeShoesOne(shoesId),HttpStatus.OK);
    }

}

