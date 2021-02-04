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

	@Autowired
	private CertificationService certificationService;

	@GetMapping("/detailcertification/{certId}")
	public CertificationDto certificationDetail (@PathVariable(value = "certId") int id) {
		CertificationDto result = certificationService.certificationDetail(id);
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

	@PutMapping("/likeUpCertification/{cngId}")
	public int likeUp ( @PathVariable(value = "cngId") int id) {
		return certificationService.likeUp(id);
	}

	@PutMapping("/likeDownCertification/{cngId}")
	public int likeDown ( @PathVariable(value = "cngId") int id) {
		return certificationService.likeDown(id);
	}

	@GetMapping("/searchWordCert/{keyword}")
	public  List<CertificationDto> searchWordCert(@PathVariable(value = "keyword") String keyword){
		System.out.println("인증리스트 중에서 "+ keyword +"로 검색해볼게요!");
		return certificationService.searchWordCert(keyword);
	}
	
}
