package com.example.ssafypjt2.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.ssafypjt2.dto.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.service.FavCategoryService;

@RestController
public class FavCategoryController {

	@Autowired
	private FavCategoryService favcategoryService;
	public static final Logger logger = LoggerFactory.getLogger(FavCategoryController.class);

	@CrossOrigin(origins = "*") // 특정 도메인만 접근을 허용해 주는 어노테이션
	@PostMapping("/signup/favCategory")
	public int favCategory(@RequestBody FavCategoryDto[] favCategoryDto, HttpServletResponse response) { // 회원가입 post 요청

		int result = 0;
		HttpStatus status = null;
		try {

			for (FavCategoryDto list : favCategoryDto) {

				System.out.println("가장 좋아하는 카테고리 insert 하기 : " + list.getCategory_id()); // 가장 좋아하는 카테고리 고르기
				result = favcategoryService.favCategory(list);
				System.out.println(result);
			}


		} catch (Exception e) {
			logger.error("카테고리 등록 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return result;
	}

	@CrossOrigin(origins = "*") // 특정 도메인만 접근을 허용해 주는 어노테이션
	@PostMapping("/userPage/UpdatefavCategory")
	public int updatefavCategory(@RequestBody FavCategoryDto[] favCategoryDto) { // 회원가입 post 요청
		System.out.println("여기 오긴 하는거야?");
		int result = 0;
		HttpStatus status = null;
		try {


			System.out.println(result);
//			for (FavCategoryDto list : favCategoryDto) {
//				System.out.println(list.getUser_id() +" 유저의 관심 카테고리 수정하기  : " + list.getCategory_id()); // 가장 좋아하는 카테고리 고르기
//				result = favcategoryService.updatefavCategory(list);
//				System.out.println(result);
//			}

			for (int i = 0; i < favCategoryDto.length; i++) {
				FavCategoryDto list = favCategoryDto[i];
				if (i == 0) {
					result = favcategoryService.deletefavCategory(list.getUser_id());
				}
				System.out.println(list.getUser_id() + " 유저의 관심 카테고리 수정하기  : " + list.getCategory_id()); // 가장 좋아하는 카테고리 고르기
				result = favcategoryService.updatefavCategory(list);
				System.out.println(result);
			}


		} catch (Exception e) {
			logger.error("카테고리 등록 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return result;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/favCategoryName/{nickName}")
	public List<CategoryDto> favCategoryName (@PathVariable String nickName){
		return favcategoryService.favCategoryName(nickName);
	}

}
