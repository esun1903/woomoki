package com.example.ssafypjt2.service;

import java.util.List;

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
		int res = dao.challengeDelete(id);
		return res;
	}

	@Override
	public CertificationDto certificationDetail(int id) {
		return dao.certificationDetail(id);
	}

	@Override
	public List<CertificationDto> certificationAllList() {
		return dao.certificationAllList();
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
	public int likeUp(int id) {
		return dao.likeUp(id);
	}

	@Override
	public int likeDown(int id) {
		return dao.likeDown(id);
	}

	@Override
	public List<CertificationDto> searchWordCert(String keyword) {
		System.out.println("서비스단 들어왔어~");
		return dao.searchWordCert(keyword);
	}

}
