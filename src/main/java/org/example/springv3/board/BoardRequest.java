package org.example.springv3.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.example.springv3.user.User;

public class BoardRequest {
    @Data
    public static class SaveDTO {
        @NotEmpty  // 공백,null X
        private String title;
        @NotEmpty
        private String content;

        public Board toEntity(User sessionUser) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();

        }
    }
}
