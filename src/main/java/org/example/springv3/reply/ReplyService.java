package org.example.springv3.reply;


import lombok.RequiredArgsConstructor;
import org.example.springv3.board.Board;
import org.example.springv3.board.BoardRepository;
import org.example.springv3.core.error.ex.Exception404;
import org.example.springv3.core.error.ex.ExceptionApi403;
import org.example.springv3.core.error.ex.ExceptionApi404;
import org.example.springv3.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service //IoC 등록
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;



    //1. 조회를 먼저 해본다 DB에서 터지는거 보다 서버에서 터지는게 더 낫다.
    // 지금 만든 것은 트랙잭션과 비즈니스 로직을 만든 거다!
    @Transactional
    public void 댓글삭제(int id, User sessionUser){

        //2.권한 체크
        //팀프로젝트에 닉네임을 찾을 거면 mfintbYid를 하나 더 만들어서 해보자!
     Reply replyPs =  replyRepository.findById(id)
             .orElseThrow(()-> new Exception404("해당 댓글을 찾을 수 없습니다."));

     //DB에 Reply를 조회했을때
     if (replyPs.getUser().getId() != sessionUser.getId()){
        throw new ExceptionApi403("댓글 삭제 권한이 없습니다.");
     }

        replyRepository.deleteById(id);
    }

    @Transactional
    public ReplyResponse.DTO 댓글쓰기(ReplyRequest.SaveDTO saveDTO, User sessionUser) {
        System.out.println(3);
        // 1. 게시글 존재 유무 확인
        Board boardPS = boardRepository.findById(saveDTO.getBoardId())
                .orElseThrow(() -> new ExceptionApi404("게시글을 찾을 수 없습니다"));

        System.out.println(4);
        // 2. 비영속 댓글 객체 만들기
        Reply reply = saveDTO.toEntity(sessionUser, boardPS);

        System.out.println(5);
        // 3. 댓글 저장 (reply가 영속화됨)
        replyRepository.save(reply);
        System.out.println(6);
        return new ReplyResponse.DTO(reply);
    }
}
