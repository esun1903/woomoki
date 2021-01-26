package com.example.ssafypjt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.CertificationDao;
import com.example.ssafypjt2.dto.CertificationDto;

@Service
public class CertificationServiceImpl implements CertificationService{
	@Autowired
	private CertificationDao dao; 
	
	@Override
	public int certificationInsert(CertificationDto certificationDto) {
		int res = dao.certificationInsert(certificationDto);
		return res;
	}

	@Override
	public int certificationUpdate(CertificationDto certificationDto) {
		int res =dao.certificationUpdate(certificationDto);
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

}
