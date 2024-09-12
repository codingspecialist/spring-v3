package org.example.springv3.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //페이지 찾고 페이지네이션 하기
    @Query("select b from Board b where b.title like %:title% order by b.id desc")
    Page<Board> mfindAll1(@Param("title") String title, Pageable pageable);

    //페이지 모두찾기
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

