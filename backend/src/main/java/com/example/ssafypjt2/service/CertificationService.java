package com.example.ssafypjt2.service;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dto.CertificationDto;

@Service
public interface CertificationService {
	public int certificationInsert(CertificationDto certificationDto);

	public int certificationUpdate(CertificationDto certificationDto);

	public int certificationDelete(int id);

	public CertificationDto certificationDetail(int id);

	public List<CertificationDto> certificationAllList();

	public List<CertificationDto> sameChallengeCrtList(int cngId);

	public List<CertificationDto> userCrtList(int userId);

	public List<CertificationDto> userCrtListSort(int userId, int cngId);

	public int likeUp(int userId, int id);

	public int likeDown(int userId, int id);

	public List<CertificationDto> searchWordCert(String keyword);

	public List<CertificationDto> user_LikeAndCertificationList(int user_id);

    public ChallengeDto challengeWeekDay(int cngId);

    public List<CertificationDto> CngCertificationList(int user_id, int cngId);

	public int certificationStamp(CertificationDto certificationDto);

	public int cancleConfirmation(CertificationDto certificationDto);
}
