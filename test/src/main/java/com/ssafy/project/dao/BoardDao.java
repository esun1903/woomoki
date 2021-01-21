package com.ssafy.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.dto.BoardDto;

@Mapper
public interface BoardDao {
	public List<BoardDto> list();
	public BoardDto detail(String id);;
	public int write(BoardDto boardDto);
	
}
