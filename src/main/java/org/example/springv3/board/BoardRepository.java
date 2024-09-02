package org.example.springv3.board;

import org.example.springv3.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {


    @Modifying
    @Transactional
    @Query("delete from Board b where b.id=:id")
    void deleteById(@Param("id") Integer id);


}

