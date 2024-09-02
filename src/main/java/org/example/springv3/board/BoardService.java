package org.example.springv3.board;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.core.error.ex.Exception404;
import org.example.springv3.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    @Transactional(readOnly = true)
    public BoardResponse.DetailDTO 게시물상세보기(User sessionUser, Integer boardId){
        Optional<Board> boardPS = boardRepository.findById(boardId);

        if(boardPS.isEmpty()){
            throw new Exception404("게시물이 없습니다.");
        }
        Board board = boardPS.get();


        if(sessionUser.getId() != board.getUser().getId()){
            throw  new Exception403("내가 적은 글이 아닙니다.");
        }


        return new BoardResponse.DetailDTO(board,sessionUser);


    }
}
