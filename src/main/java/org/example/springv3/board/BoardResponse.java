package org.example.springv3.board;

import lombok.Data;
import org.example.springv3.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isOwner;
        private Integer userId;
        private String username;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isOwner = false;

            if (sessionUser != null) {
                if (board.getUser().getId() == sessionUser.getId()) {
                    isOwner = true; // 권한체크

                }
            }
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
        }
    }
}
