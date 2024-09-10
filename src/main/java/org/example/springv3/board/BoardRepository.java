package org.example.springv3.board;

import org.example.springv3.user.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {


    @Query("select b from Board b where b.title like %:title% order by b.id desc")
    List<Board> mfindAll(@Param("title") String title);


    //@Query("select b from Board b join fetch b.user u left join fetch b.replies r left join fetch r.user where b.id=:id")
    @Query("select b from Board b join fetch b.user u left join fetch b.replies r left join fetch r.user where b.id=:id")
    Optional<Board> mFindByIdWithReply(@Param("id") int id);


    //join 쿼리
    //@Query(value = "select * from board_tb bt inner join user_tb on bt.user_id = ut.id wherebt.id =?", nativeQuery = true)
    @Query("select b from Board b join fetch b.user u where b.id=:id")
    //하나를 찾을 때는 Optional을 쓰는게 좋다.
    Optional<Board> mFindById(@Param("id") int id);






}

