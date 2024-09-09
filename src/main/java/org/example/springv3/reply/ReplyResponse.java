package org.example.springv3.reply;

import lombok.Data;

public class ReplyResponse {

    @Data
    public static class DTO {
        private Integer id;
        private String comment;
        private String username;

        public DTO(Reply reply) {
            this.id = reply.getId();
            System.out.println("--1");
            this.comment = reply.getComment();
            System.out.println("--2");
            this.username = reply.getUser().getUsername();
            System.out.println("--3");
        }
    }
}
