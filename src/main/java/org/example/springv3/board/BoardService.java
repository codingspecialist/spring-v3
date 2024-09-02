package org.example.springv3.board;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception400;
import org.example.springv3.core.error.ex.Exception403;
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


    public List<Board> 게시글_목록보기() {
        return boardRepository.findAll();
    }

    @Transactional
    public void 게시글_삭제하기(Integer id, User sessionUser) {


        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new Exception400("존재하지 않는 게시글입니다.");
        }

        if (board.get().getUser().getId() != sessionUser.getId()) {
            throw new Exception403("작성자가 아닙니다.");
        }

        boardRepository.deleteById(id);
    }


}
