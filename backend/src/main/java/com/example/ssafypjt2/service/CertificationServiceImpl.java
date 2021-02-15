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



}
