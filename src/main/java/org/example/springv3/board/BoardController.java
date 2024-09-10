package org.example.springv3.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception404;
import org.example.springv3.core.error.ex.ExceptionApi404;
import org.example.springv3.core.util.Resp;
import org.example.springv3.user.User;
import org.example.springv3.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;





    @GetMapping("/test/v1")
    //@ResposeBody 붙이지 않으면 데이터를 받지 못 한다.
    public @ResponseBody Resp testV1(){
        User u = new User();
        u.setId(1);
        u.setUsername("ssar");
        u.setPassword("1234");
        u.setEmail("ssar@gmail.com");

        return Resp.ok(u);
    }

    @GetMapping("/test/v2")
    //@ResposeBody 붙이지 않으면 데이터를 받지 못 한다.
    public @ResponseBody Resp testV2(){
        User u = new User();
        u.setId(1);
        u.setUsername("ssar");
        u.setPassword("1234");
        u.setEmail("ssar@gmail.com");

        User u1 = new User();
        u1.setId(1);
        u1.setUsername("cos");
        u1.setPassword("1234");
        u1.setEmail("ssar@gmail.com");
        //JSON 어레이
        List<User> users = Arrays.asList(u, u1);
        return Resp.ok(users);
    }

    @GetMapping("/test/v3")
    //@ResposeBody 붙이지 않으면 데이터를 받지 못 한다.
    public @ResponseBody Resp testV3(){
        //데이터를 응답 해달라는 방식이다.
        // throw new Exception404("유저를 찾을 수 없습니다."); 이렇게 클라이언트한테 보내주면 안된다.
        // F12 눌려서 확인해보면 200으로 응답을 한다. 그러면 안된다.

        return Resp.fail(404,"유저를 찾을 수 없습니다.");
    }

    @GetMapping("/test/v4")
    public @ResponseBody Resp testV4(HttpServletResponse response){
        response.setStatus(404);
        return Resp.fail(404, "유저를 찾을 수 없습니다");
    }

    @GetMapping("/test/v5")
    public ResponseEntity<?> testV5(){ // 1. ResponseBody 생략, 상태코드를 넣을 수 있따.
        return new ResponseEntity<>(Resp.fail(404, "찾을 수 없습니다"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/test/v6")
    public ResponseEntity<?> testV6(){ // 1. ResponseBody 생략, 상태코드를 넣을 수 있따.
        throw new ExceptionApi404("페이지를 찾을 수 없습니다.");
    }





    // localhost:8080?title=제목
    @GetMapping("/")
    //required = false 이걸 넣으면 주소창에 아무것도 안 넣어도 화면이 보인다.
    public String list(@RequestParam(name = "title", required = false) String title, HttpServletRequest request) {
        List<Board> boardList = boardService.게시글목록보기(title);
        request.setAttribute("models", boardList);
        return "board/list";
    }


    @PostMapping("/api/board/{id}/delete")
    public String removeBoard(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글삭제하기(id, sessionUser);
        return "redirect:/";
    }


    @GetMapping("/api/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    @PostMapping("/api/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.게시글쓰기(saveDTO, sessionUser);

        return "redirect:/";
    }

    @GetMapping("/v2/api/board/{id}/update-form")
    public @ResponseBody BoardResponse.DTO updateForm(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DTO model = boardService.게시글수정화면V2(id, sessionUser);
        return model;
    }

    @GetMapping("/api/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.게시글수정화면(id, sessionUser);
        request.setAttribute("model", board);
        return "board/update-form";
    }

    @PostMapping("/api/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글수정(id, updateDTO, sessionUser);
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO model = boardService.게시글상세보기(sessionUser, id);
        request.setAttribute("model", model);

        return "board/detail";
    }

    @GetMapping("/v2/board/{id}")
    public @ResponseBody BoardResponse.DetailDTO detailV2(@PathVariable("id") Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO model = boardService.게시글상세보기(sessionUser, id);

        return model;
    }

    @GetMapping("/v3/board/{id}")
    public @ResponseBody Board detailV3(@PathVariable("id") Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Board model = boardService.게시글상세보기V3(sessionUser, id);

        return model;
    }

}
