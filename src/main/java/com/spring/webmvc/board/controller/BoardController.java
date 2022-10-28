package com.spring.webmvc.board.controller;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board") // 공통 URL 진입점 설정
@Log4j2
public class BoardController {

    private final BoardService service;

    // 게시물 목록 조회 요청 처리
    //    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping("/list")
        public String list(Model model) {
            int a = 10;
            List<Board> boardList = service.getList(); // 랜더링해야됨
            log.info("/board/list GET 요청 - {} ", a);
            model.addAttribute("bList", boardList);

            return "board/list";
                /* 밑으로갈수록 심각함
                    TRACE   -   자잘한 로그
                    DEBUG   -   개발단계의 디버깅
                    INFO    -   정보
                    WARN    -   경고
                    ERROR   -   심각한 에러
                */
        }

        // 게시물 상세 조회 요청 처리
        @GetMapping("/content/{bno}")
        public String content(@PathVariable("bno") Long boardNo, Model model) {
            log.info("/board/content/{}GET", boardNo);
            Board board=service.getDetail(boardNo);
            model.addAttribute("b", board); // ctrl alt n ,v 인라인화 --> 한번만 쓰는거라서
            return "board/detail";
        }
        // 게시물 쓰기 화면 요청
        @GetMapping("/write")
        public String write () {
            log.info("/board/write GET");
            return "board/write";
        }

        // 게시물 등록 요청
        @PostMapping("/write")
        // Model로 하면 중간에 request가 죽어버리기 때문에 RedirectAttribute 사용
        public String write (Board board, RedirectAttributes ra) {
            log.info("/board/write POST {}", board);
            // db에 넣어주고
            boolean flag=service.insert(board);
            ra.addFlashAttribute("msg", "insert-success");
            // 갱신된 board/list를 가져와야되니까 redirect - 이미 위에서 한번 요청했으니까 재요청하는부분
            return flag ? "redirect:/board/list" : "redirect:/";
        }

}
