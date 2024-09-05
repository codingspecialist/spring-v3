package org.example.springv3.reply;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springv3.board.Board;
import org.example.springv3.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@Table(name = "reply_tb")
@NoArgsConstructor
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment; // 댓글 내용

    @JsonIgnoreProperties({"password"})
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;     //N포드 생각

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp // em.persist 할때 발동
    private Timestamp createdAt;
}
