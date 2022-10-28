package com.spring.webmvc.board.service;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

// 역할 : 컨트롤러와 레파지토리 사이 중간에서 잡다한 처리를 담당
@RequiredArgsConstructor // final필드 초기화 생성자를 만듬
@Service // Bean등록
public class BoardService {

    // autowired를(이자리에서 필드주입을)쓰면 변동될 가능성이 생기기때문에 final
    private final BoardRepository repository;
    // @RequiredArgsConstructor 붙이면 안써도됨
    // 그래서 생성자 주입을 함
//    @Autowired
//    public BoardService(BoardRepository repository) {
//        this.repository = repository;
//    }

    // 전체 조회 중간처리
    public List<Board> getList() {
        List<Board> boardList = repository.findAll();
        return boardList;
    }
    // 상세 조회 중간처리
    public Board getDetail(Long boardNo) {
        Board board= repository.findOne(boardNo);
        return board;
    }
    // 게시물 저장 중간처리
    public boolean insert(Board board) {
        boolean flag = repository.save(board);
        return flag;
    }

    // 게시물 수정 중간처리
    public boolean update(Board board) {
        boolean flag= repository.modify(board);
        return flag;
    }

    // 게시물 삭제 중간처리
    public boolean delete(Long boardNo) {
        boolean flag = repository.remove(boardNo);
        return flag;
    }

}

