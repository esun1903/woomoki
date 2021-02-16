package com.example.ssafypjt2.service;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dao.CertificationDao;
import com.example.ssafypjt2.dto.CertificationDto;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationDao dao;

    @Override
    public int certificationInsert(CertificationDto certificationDto) {
        int res = dao.certificationInsert(certificationDto);
        return res;
    }

    @Override
    public int certificationUpdate(CertificationDto certificationDto) {
        int res = dao.certificationUpdate(certificationDto);
        return res;
    }

    @Override
    public int certificationDelete(int id) {
        int res = dao.ceriticationDelete(id);
        return res;
    }

    @Override
    public CertificationDto certificationDetail(int id) {
        return dao.certificationDetail(id);
    }

    @Override
    public List<CertificationDto> certificationAllList() {
        System.out.println("여기?");
        List<CertificationDto> list = dao.certificationAllList();
        System.out.println(list);
        return list;
    }

    @Override
    public List<CertificationDto> sameChallengeCrtList(int cngId) {
        return dao.sameChallengeCrtList(cngId);
    }

    @Override
    public List<CertificationDto> userCrtList(int userId) {
        return dao.userCrtList(userId);
    }

    @Override
    public List<CertificationDto> userCrtListSort(int userId, int cngId) {
        return dao.userCrtListSort(userId, cngId);
    }

    @Override
    public int likeUp(int userId, int cert_id) {
        int count= dao.one_likeUp(cert_id);
        return dao.likeUp(userId, cert_id);
    }

    @Override
    public int likeDown(int userId, int cert_id) {
        int count = dao.one_likeDown(cert_id);
        return dao.likeDown(userId, cert_id);
    }


	@Override
	public List<CertificationDto> searchWordCert(String keyword) {
		System.out.println("서비스단 들어왔어~");
		return dao.searchWordCert(keyword);
	}

    @Override
    public List<CertificationDto> user_LikeAndCertificationList(int user_id) {
        List<CertificationDto> list = dao.main_LikeCertificationList(user_id);
        return list;
    }

    @Override
    public ChallengeDto challengeWeekDay(int cngId) {
        System.out.println("서비스 단 들어왔다");
        ChallengeDto count = dao.challengeWeekDay(cngId);
        return count;
    }

    @Override
    public List<CertificationDto> CngCertificationList(int user_id, int cngId) {
        System.out.println("서비스 단 들어왔다");
        List<CertificationDto> result = dao.CngCertificationList(user_id,cngId);
        return result;
    }

    @Override
    public int certificationStamp(CertificationDto certificationDto) {
        System.out.println("서비스 단 들어왔다");
        int result = 1;
        int cng_id = certificationDto.cng_id;
        int user_id = certificationDto.user_id;
        int stampPlus = dao.certificationStamp(user_id,cng_id); // ++ 하는 것
        int stampCount = dao.certificationStampCount(user_id,cng_id); // 현재 stampCount의 개수를 구하기
        ChallengeDto  cngDto = dao.challengeDetail(cng_id); // 챌린지의 week, day를 가져오기

        int week = cngDto.getWeek();
        int day = cngDto.getDay();
        // 만약, 마지막 인증이라면
        if( stampCount  >= week * day) {
            // 챌린지가 성공했다는 것으로 바뀌고
             dao.challengeSuccess(user_id,cng_id);
            // 레벨이  ++ 해진다.
             dao.userLevelUp(user_id);
              result = 2;
        }

        return result;
    }

    @Override
    public int cancleConfirmation(CertificationDto certificationDto) {
        System.out.println("서비스 단 들어왔다");
        int result = 1;
        int cng_id = certificationDto.cng_id;
        int user_id = certificationDto.user_id;
        int stampPlus = dao.canclecertificationStamp(user_id,cng_id); // -- 하는 것
        int stampCount = dao.certificationStampCount(user_id,cng_id); // 현재 stampCount의 개수를 구하기
        ChallengeDto  cngDto = dao.challengeDetail(cng_id); // 챌린지의 week, day를 가져오기

        int week = cngDto.getWeek();
        int day = cngDto.getDay();
        // 만약, 마지막 인증 -1 이었다면 진행중으로 변경
        if( stampCount+1 == week * day) {
            // 챌린지가 성공했다는 것으로 바뀌고
            dao.challengeFail(user_id,cng_id);
            // 레벨이  ++ 해진다.
            dao.userLevelDown(user_id);
            result = 0;
        }

        return result;
    }


}
