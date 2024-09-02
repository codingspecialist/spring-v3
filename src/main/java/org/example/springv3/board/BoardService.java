package org.example.springv3.board;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.core.error.ex.Exception404;
import org.example.springv3.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    @Transactional
    public BoardResponse.DetailDTOV2 게시물상세보기(Integer boardId, User sessionUser){
        Optional<Board> boardPS = boardRepository.findById(boardId);

        Board board = boardPS.get();

        return new BoardResponse.DetailDTOV2(board,sessionUser);
    }

    public List<Board> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }
}
