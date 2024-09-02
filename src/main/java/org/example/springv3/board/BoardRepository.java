package org.example.springv3.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public void updateById(String title, String content, int id) {
        Query query = em.createNativeQuery("update board_tb set title =?, content =? where id =?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
        }
    }
}
