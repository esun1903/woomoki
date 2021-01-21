package com.ssafy.project.service;

import java.util.List;

import com.ssafy.project.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> list();
	public BoardDto detail(String id);
	public int write(BoardDto boarddto);
}
