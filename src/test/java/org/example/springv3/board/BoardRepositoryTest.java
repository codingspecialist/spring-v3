package org.example.springv3.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest // JpaRepository를 상속하면 import 안해도 된다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void mFindByIdWithReply_test(){
        Board board = boardRepository.mFindByIdWithReply(5).get();
        System.out.println(board.getReplies().get(0).getComment());
    }


}
