package org.example.springv3.board;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.user.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;


    public Board 게시글수정화면(int id, User sessionUser) {
        Board board = boardRepository.findById(id);

        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글 수정 권한이 없습니다.");
        }
        return board;
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO, User sessionUser) {
        // 1. 게시글 조회 (없으면 404)
        Board board = boardRepository.findById(id);

        // 2. 권한체크
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
        // 3. 게시글 수정
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());

    }

}
