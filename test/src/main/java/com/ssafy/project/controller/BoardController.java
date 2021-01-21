package com.ssafy.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.dto.BoardDto;
import com.ssafy.project.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@GetMapping("/list")
	public ResponseEntity<List<BoardDto>> list() {
		List<BoardDto> list = boardService.list();
		return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardDto> detail(@PathVariable(value = "boardId") String id) {
		BoardDto board = boardService.detail(id);
		return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<Integer> write(@RequestBody BoardDto boarddto) {
		int result = boardService.write(boarddto);
		if (result == 1) {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}