package com.ssafy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.dao.BoardDao;
import com.ssafy.project.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardDto> list() {
		return boardDao.list();
	}

	@Override
	public BoardDto detail(String id) {
		return boardDao.detail(id);
	}

	@Override
	public int write(BoardDto boarddto) {
		return boardDao.write(boarddto);
	}
}
