package org.example.springv3.board;

import lombok.Data;
import org.example.springv3.reply.Reply;
import org.example.springv3.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {
    @Data
    public static class DTO {
        private Integer id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Boolean isOwner;
        private String username;
        private List<ReplyDTO> replies = new ArrayList<>();

        // DTO를 Entity로 만들면 안된다. (이유)
        // (1) Lazy Loading하면서 no session 오류
        // (2) 양방향 매핑때문에 json 만들면서 무한 루프
        @Data
        class ReplyDTO {
            private Integer id;
            private String comment;
            private String username;
            private Boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.username = reply.getUser().getUsername();
                this.isOwner = false;

                if (sessionUser != null) {
                    if (reply.getUser().getId() == sessionUser.getId()) {
                        isOwner = true; // 권한체크
                    }
                }
            }
        }

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
            this.username = board.getUser().getUsername();

            for (Reply reply : board.getReplies()) {
                replies.add(new ReplyDTO(reply, sessionUser));
            }
        }
    }
}
