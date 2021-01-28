package com.example.ssafypjt2.service;

import java.util.List;

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
}
