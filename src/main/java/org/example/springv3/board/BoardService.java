package org.example.springv3.board;

import lombok.RequiredArgsConstructor;
import org.example.springv3.core.error.ex.Exception403;
import org.example.springv3.core.error.ex.Exception404;
import org.example.springv3.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;

    public List<Board> 게시글목록보기() {
        //Pageable pg = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boardList = boardRepository.findAll(sort);
        return boardList;
    }


    @Transactional
    public void 게시글삭제하기(Integer id, User sessionUser) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다"));

        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("작성자가 아닙니다.");
        }

        boardRepository.deleteById(id);
    }



    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {

        Board boardEntity = saveDTO.toEntity(sessionUser);
        boardRepository.save(boardEntity);
    }

public BoardResponse.DTO 게시글수정화면V2(int id, User sessionUser) {
    Board board = boardRepository.findById(id)
            .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다"));

    if (board.getUser().getId() != sessionUser.getId()) {
        throw new Exception403("게시글 수정 권한이 없습니다.");
    }
    return new BoardResponse.DTO(board);
}

    public Board 게시글수정화면(int id, User sessionUser) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다"));

        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글 수정 권한이 없습니다.");
        }
        return board;
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO, User sessionUser) {
        // 1. 게시글 조회 (없으면 404)
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없습니다"));

        // 2. 권한체크
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
        // 3. 게시글 수정
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());

    }

    
    public BoardResponse.DetailDTO 게시글상세보기(User sessionUser, Integer boardId){
        Board boardPS = boardRepository.mFindByIdWithReply(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다."));
        return new BoardResponse.DetailDTO(boardPS, sessionUser);
    }

    public Board 게시글상세보기V3(User sessionUser, Integer boardId){
        Board boardPS = boardRepository.mFindByIdWithReply(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다."));
        return boardPS;
    }
}
