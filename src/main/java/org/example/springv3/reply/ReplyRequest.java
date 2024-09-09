package org.example.springv3.reply;

import lombok.Data;
import org.example.springv3.board.Board;
import org.example.springv3.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;
    }
}
