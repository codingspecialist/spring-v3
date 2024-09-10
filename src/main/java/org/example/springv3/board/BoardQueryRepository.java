package org.example.springv3.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
//Query가 복잡하면 여기에 써야한다.
public class BoardQueryRepository {
    private final EntityManager em;
}
