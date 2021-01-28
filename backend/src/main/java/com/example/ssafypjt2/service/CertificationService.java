package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dto.CertificationDto;

@Service
public interface CertificationService {

	  	public int certificationInsert(CertificationDto certificationDto); 
	    public int certificationUpdate(CertificationDto certificationDto);
	    public int certificationDelete(int id); 
	    public CertificationDto certificationDetail(int id);
	    
	   
}
