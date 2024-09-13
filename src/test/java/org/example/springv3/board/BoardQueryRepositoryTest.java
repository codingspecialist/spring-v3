package org.example.springv3.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardQueryRepository.class) //JPA Repository가 아니어서, Import 필요함
@DataJpaTest
public class BoardQueryRepositoryTest {
    @Autowired
    private BoardQueryRepository boardQueryRepository;

    @Test
    public void selectV1_test(){
        // given

        // when
        List<BoardResponse.ListDTO> boardList = boardQueryRepository.selectV1();

        // eye
        System.out.println(boardList);
    }
}
