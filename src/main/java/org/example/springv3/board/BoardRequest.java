package org.example.springv3.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.example.springv3.user.User;

public class BoardRequest {
    @Data
    public static class SaveDTO { // title, content 2개만 담으면 된다.
        //@Pattern(regexp = ) 정규표현식 패턴
        @NotEmpty
        private String title;
        @NotEmpty(message = "비워놓지마.")
        private String content;

        public Board toEntity(User sessionUser) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)

                    .build(); // shift + enter
        }
    }


    @Data
    public static class UpdateDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;

    }
}
