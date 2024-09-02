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

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {


    @Modifying
    @Transactional
    @Query("delete from Board b where b.id=:id")
    void deleteById(@Param("id") Integer id);



    @Query("select b from Board b where b.id=:id")
    Board findById(@Param("id") int id);

    Optional<Board> findById(Integer boardId);

}

