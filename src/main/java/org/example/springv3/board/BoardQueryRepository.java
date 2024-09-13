package org.example.springv3.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final EntityManager em;

    public List<BoardResponse.ListDTO> selectV1(){
        String sql = """
            select id, title, (select count(id) from reply_tb where board_id = bt.id) count
            from board_tb bt;
                """;

        Query query = em.createNativeQuery(sql);

        JpaResultMapper mapper = new JpaResultMapper();
        List<BoardResponse.ListDTO> boardList = mapper.list(query, BoardResponse.ListDTO.class);
        return boardList;
    }
}
