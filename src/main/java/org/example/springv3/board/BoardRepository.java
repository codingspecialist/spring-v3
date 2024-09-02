package org.example.springv3.board;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
/*
    @Query("select u from  User u where u.username=:usrname")
    User findByUsername(@Param("username") String username);
*/
    @Query("select b from Board b where b.id=:id")
    Board findById(@Param("id") int id);


}
