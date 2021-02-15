package com.example.ssafypjt2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssafypjt2.dto.CertificationDto;
import com.example.ssafypjt2.service.CertificationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CertificationController {
	// test 하고 싶어요 !

	@Autowired
	private CertificationService certificationService;

	@GetMapping("/detailCertification/{certId}")
	public CertificationDto certificationDetail (@PathVariable(value = "certId") int id) {
		CertificationDto result = certificationService.certificationDetail(id);
		System.out.println(result);
		return result;
	}


	@PostMapping("/insertCertification")
	public int certificationInsert ( @RequestBody CertificationDto certificationDto) {
		int result = certificationService.certificationInsert(certificationDto);
		return result;
	}

	@PutMapping("/updateCertification")
	public int challengeUpdate ( @RequestBody CertificationDto certificationDto) {
		int result = certificationService.certificationUpdate(certificationDto);
		return result;
	}

	@DeleteMapping("/deleteCertification/{certId}")
	public int certificationUpdate (@PathVariable(value = "certId") int id) {
		int result = certificationService.certificationDelete(id);
		return result;
	}

	@GetMapping("/allCertification")
	public  List<CertificationDto> certificationAllList(){
		System.out.println("여기왔다.");
		return certificationService.certificationAllList();

	}
	@GetMapping("/sameChallengeCertification/{cngId}")
	public  List<CertificationDto> sameChallengeCrtList(@PathVariable(value = "cngId") int cngId){
		return certificationService.sameChallengeCrtList(cngId);

	}
	@GetMapping("/userCertification/{userId}")
	public  List<CertificationDto> userCrtList(@PathVariable(value = "userId") int userId){
		return certificationService.userCrtList(userId);

	}
	@GetMapping("/userCertificationSort/{userId}/{cngId}")
	public  List<CertificationDto> userCrtListSort(@PathVariable(value = "userId") int userId, @PathVariable(value = "cngId") int cngId){
		return certificationService.userCrtListSort(userId, cngId);
	}

	@PutMapping("/likeUpCertification/{userId}/{certId}")
	public int likeUp (@PathVariable(value = "userId") int userId,   @PathVariable(value = "certId") int cert_id) {
		System.out.println(userId +"가 " + cert_id +"인증 좋아요를 누를게  ");
		return certificationService.likeUp(userId, cert_id );
	}

	@PutMapping("/likeDownCertification/{userId}/{certId}")
	public int likeDown (  @PathVariable(value = "userId") int userId, @PathVariable(value = "certId") int cert_id) {
		System.out.println(userId +"가 " + cert_id +"인증 좋아요를 취소할게 ㅎ  ");
		return certificationService.likeDown(userId, cert_id );
	}

	@GetMapping("/searchWordCert/{keyword}")
	public  List<CertificationDto> searchWordCert(@PathVariable(value = "keyword") String keyword){
		System.out.println("인증리스트 중에서 "+ keyword +"로 검색해볼게요!");
		return certificationService.searchWordCert(keyword);
	}


	//챌린지id , user_id 를 받으면 -> 현재의 챌린지를 알려줌


	
}


