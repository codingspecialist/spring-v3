package org.example.springv3.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Optional<Board> findById(Integer boardId);

}
