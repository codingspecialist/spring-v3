package org.example.springv3.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Setter
@Getter
@Table(name = "user_tb")
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username; // 아이디
    @Column(nullable = false)
    private String password; // 해쉬 저장
    @Column(nullable = false)
    private String email;
    // 이메일 인증 여부
    // 19세이상 여부

    private String profile;

    @CreationTimestamp
    private Timestamp createdAt;

    // 로그인 시도 5번 이상 틀렸는지!! 틀린횟수
    // 잠김여부
    // 활성화여부
    // 탈퇴여부, 탈퇴시간
    // 로그인시에 device 장비 (window, mac, 휴대폰, 컴퓨터)
    // ip 저장

    @Builder
    public User(Integer id, String username, String password, String email, String profile, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.createdAt = createdAt;
    }
}