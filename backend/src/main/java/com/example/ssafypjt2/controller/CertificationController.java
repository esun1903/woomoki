package com.example.ssafypjt2.controller;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.UserDto;
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

    @GetMapping("/detailCertification/{certId}")
    public CertificationDto certificationDetail(@PathVariable(value = "certId") int id) {
        CertificationDto result = certificationService.certificationDetail(id);
        System.out.println(result);
        return result;
    }

    @PostMapping("/insertCertification")
    public int certificationInsert(@RequestBody CertificationDto certificationDto) {
        // 만약, week , day 가 마지막이라면 ? ->
        int result = certificationService.certificationInsert(certificationDto);
        return result;
    }

    @PutMapping("/updateCertification")
    public int challengeUpdate(@RequestBody CertificationDto certificationDto) {
        int result = certificationService.certificationUpdate(certificationDto);
        return result;
    }

    @DeleteMapping("/deleteCertification/{certId}")
    public int certificationUpdate(@PathVariable(value = "certId") int id) {
        int result = certificationService.certificationDelete(id);
        return result;
    }

    @GetMapping("/allCertification")
    public List<CertificationDto> certificationAllList() {
        return certificationService.certificationAllList();
    }

    @GetMapping("/sameChallengeCertification/{cngId}")
    public List<CertificationDto> sameChallengeCrtList(@PathVariable(value = "cngId") int cngId) {
        return certificationService.sameChallengeCrtList(cngId);
    }

    @GetMapping("/userCertification/{userId}")
    public List<CertificationDto> userCrtList(@PathVariable(value = "userId") int userId) {
        return certificationService.userCrtList(userId);

    }

    @GetMapping("/userCertificationSort/{userId}/{cngId}")
    public List<CertificationDto> userCrtListSort(@PathVariable(value = "userId") int userId, @PathVariable(value = "cngId") int cngId) {
        return certificationService.userCrtListSort(userId, cngId);
    }

    @PutMapping("/likeUpCertification/{userId}/{certId}")
    public int likeUp(@PathVariable(value = "userId") int userId, @PathVariable(value = "certId") int cert_id) {
        return certificationService.likeUp(userId, cert_id);
    }

    @PutMapping("/likeDownCertification/{userId}/{certId}")
    public int likeDown(@PathVariable(value = "userId") int userId, @PathVariable(value = "certId") int cert_id) {
        return certificationService.likeDown(userId, cert_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("LikeAndCertification/{certId}")
    public List<UserDto> user_LikeAndCertificationList(@PathVariable(value = "certId") int cert_id) throws Exception {
        System.out.println(cert_id + "를 좋아요한 사람들의 list");
        List<UserDto> result = certificationService.user_LikeAndCertificationList(cert_id);
        System.out.println(result);
        return result;
    }

    @GetMapping("/searchWordCert/{keyword}")
    public List<CertificationDto> searchWordCert(@PathVariable(value = "keyword") String keyword) {
        System.out.println("인증리스트 중에서 " + keyword + "로 검색해볼게요!");
        return certificationService.searchWordCert(keyword);
    }

    @GetMapping("/certification/{cngId}")
    public ChallengeDto challengeWeekDay(@PathVariable(value = "cngId") int cngId) {
        return certificationService.challengeWeekDay(cngId);
    }

    @GetMapping("/certification/{userid}/{cngId}")
    public List<CertificationDto> CngCertificationList(@PathVariable(value = "userid") int user_id, @PathVariable(value = "cngId") int cngId) {
        System.out.println(user_id + "가 가입한 챌린지" + cngId + "의 인증을 week, day를 리스트로 리턴해주기");
        return certificationService.CngCertificationList(user_id, cngId);
    }

    @GetMapping("/confirmstatus/{userid}/{cngId}")
    public int[][] ConfirmStatus(@PathVariable(value = "userid") int user_id, @PathVariable(value = "cngId") int cngId) {
        System.out.println(user_id + "가 가입한 챌린지" + cngId + "의 인증을 certId, result 를 리스트로 리턴해주기");
        int[][] result = certificationService.ConfirmStatus(user_id, cngId);
        System.out.println(result);
        return result;
    }

    //     1) 개설자가 도장을 클릭 시
//     2) 해당 joined_challenge의
//	   3) sum_stamp ++ 하고
//	   4) 만약, 그 결과(week/day)  week * day 와 같다면
//	   5) joined_challnege result를 3으로 변경(성공한 챌린지)
//	   6) 성공한 챌린지가 변경되면 -> user의 level의 변경 됨
    @PostMapping("/completeConfirmation")
    public int completeConfirmation(@RequestBody CertificationDto certificationDto) {
        int result = certificationService.certificationStamp(certificationDto);
        if (result == 1) {
            System.out.println("도장찍기 완료");
        } else {
            System.out.println(" 챌린지가 끝났고 성공한챌린지로 변경되고 -> 레벨업이 되었습니다.");
        }
        return result;
    }

    @PostMapping("/cancleConfirmation")
    public int cancelConfirmation(@RequestBody CertificationDto certificationDto) {
        int result = certificationService.cancleConfirmation(certificationDto);
        return result;
    }

    @PostMapping("/rejectConfirmation")
    public int rejectConfirmation(@RequestBody CertificationDto certificationDto) {
        // 만약, week , day 가 마지막이라면 ? ->
        System.out.println("이 인증은 잘못된 인증입니다." + certificationDto);
        int result = certificationService.rejectConfirmation(certificationDto);
        System.out.println("잘못된 인증 성공완료");
        return result;
    }
}


