package org.example.springv3.board;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception400;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.example.springv3.core.error.ex.Exception404;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    public List<Board> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }


    @Transactional
    public void 게시글삭제하기(Integer id, User sessionUser) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isEmpty()) {
            throw new Exception400("존재하지 않는 게시글입니다.");
        }

        if (board.get().getUser().getId() != sessionUser.getId()) {
            throw new Exception403("작성자가 아닙니다.");
        }

        boardRepository.deleteById(id);
    }



    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {

        Board boardEntity = saveDTO.toEntity(sessionUser);
        boardRepository.save(boardEntity);
    }

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
        // 3. 게시글 수정하기
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());

    }

    
    public BoardResponse.DetailDTO 게시글상세보기(User sessionUser, Integer boardId){
        Board boardPS = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다."));

        if(sessionUser.getId() != boardPS.getUser().getId()){
            throw  new Exception403("내가 적은 글이 아닙니다.");
        }


        return new BoardResponse.DetailDTO(boardPS,sessionUser);
    }
}
