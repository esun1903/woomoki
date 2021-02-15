package com.example.ssafypjt2.controller;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
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
		// 만약, week , day 가 마지막이라면 ? ->
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
	public int likeDown ( @PathVariable(value = "userId") int userId, @PathVariable(value = "certId") int cert_id) {
		System.out.println(userId +"가 " + cert_id +"인증 좋아요를 취소할게 ㅎ  ");
		return certificationService.likeDown(userId, cert_id );
	}

	//내가 좋아요 한 챌린지의 id와 좋아요 수
	@CrossOrigin(origins = "*")
	@GetMapping("LikeAndCertification/{userid}")
	public List<CertificationDto> user_LikeAndCertificationList(@PathVariable(value = "userid") int user_id ) throws Exception {
		System.out.println(user_id+"가 좋아요 한 인증들과 좋아요의 수를 보여줄게");
		List<CertificationDto> result = certificationService.user_LikeAndCertificationList(user_id);
		System.out.println(result);
		return result;
	}

	@GetMapping("/searchWordCert/{keyword}")
	public  List<CertificationDto> searchWordCert(@PathVariable(value = "keyword") String keyword){
		System.out.println("인증리스트 중에서 "+ keyword +"로 검색해볼게요!");
		return certificationService.searchWordCert(keyword);
	}

//   포도알 기능
//	 userid / cngid 를 가면  week, day를 보내주고
	@GetMapping("/certification/{cngId}")
	public ChallengeDto challengeWeekDay( @PathVariable(value = "cngId") int cngId){
	    // week/day를
		System.out.println("챌린지의 "+ cngId+" 총 챌린지의 수 리턴");
		return certificationService.challengeWeekDay(cngId);
	}
//	 포도알의 인증Dto를 list로 리턴
    @GetMapping("/certification/{userid}/{cngId}")
    public List<CertificationDto> CngCertificationList(@PathVariable(value = "userid") int user_id , @PathVariable(value = "cngId") int cngId){
	  // week/day를
	   System.out.println(user_id+"가 가입한 챌린지"+cngId+"의 인증을 week, day를 리스트로 리턴해주기");
	  return certificationService.CngCertificationList(user_id, cngId);
    }
	
}


