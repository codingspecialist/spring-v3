package org.example.springv3.board;

import lombok.Data;
import org.example.springv3.reply.Reply;
import org.example.springv3.user.User;
import org.hibernate.annotations.CreationTimestamp;

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

        //댓글들이 들어와야한다.
        private List<ReplyDTO> replies = new ArrayList<>();



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

    @Data
    public static class ReplyDTO{

        private Integer id;
        private String content;
        private boolean isOwner;
        private String username;


        public ReplyDTO(Reply reply, User sesstionUser) {
            this.id = reply.getId();
            this.content = reply.getComment();
            this.username = reply.getUser().getUsername();
            this.isOwner = false;



            if (sesstionUser != null) {
                if (reply.getUser().getId() == sesstionUser.getId()) {
                    isOwner = true; //권한체크
                }
            }

        }








    }
}
