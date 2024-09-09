package org.example.springv3.reply;

import lombok.Data;
import org.example.springv3.board.Board;
import org.example.springv3.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        // insert into reply_tb(comment, board_id, user_id, created_at) values('댓글', 5, 1, now())
        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment)
                    .user(sessionUser)
                    .board(board)
                    .build();
        }
    }
}
