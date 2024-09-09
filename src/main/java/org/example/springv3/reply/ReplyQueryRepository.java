package org.example.springv3.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyQueryRepository {
    private final EntityManager em;

    public Reply findById(int id){
        Query query = em.createNativeQuery("select * from reply_tb where id = ?", Reply.class);
        return (Reply) query.getSingleResult();
    }

    public void insert(ReplyRequest.SaveDTO saveDTO, Integer userId){
        Query query = em.createNativeQuery("insert into reply_tb(comment, board_id, user_id, created_at) values(?, ?, ?, now())");
        query.setParameter(1, saveDTO.getComment());
        query.setParameter(2, saveDTO.getBoardId());
        query.setParameter(3, userId);
        query.executeUpdate();
    }

    public int maxId(){
        Query query = em.createNativeQuery("select max(id) from reply_tb", Long.class);
        Long replyId = (Long) query.getSingleResult();
        return replyId.intValue();
    }
}
