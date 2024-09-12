package org.example.springv3.board;

import lombok.Data;
import org.example.springv3.reply.Reply;
import org.example.springv3.user.User;
import org.springframework.data.domain.Page;

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

    @Data
    public static class PageDTO{
        private Integer number;// 현재 페이지
        private Integer totalPage; // 전체페이지 개수
        private Integer size;//한페이지에 아이템 개수
        private Boolean first;
        private Boolean last;
        private Integer prev; //현재페이지 -1
        private Integer next;// 현재페이지+1
        private List<Content>contents = new ArrayList<>();

        public PageDTO(Page<Board> BoardPG) {
            this.number = BoardPG.getNumber();
            this.totalPage = BoardPG.getTotalPages();
            this.size = BoardPG.getSize();
            this.first = BoardPG.isFirst();
            this.last = BoardPG.isLast();
            this.prev = BoardPG.getNumber()-1;
            this.next = BoardPG.getNumber()+1;
            for (Board board : BoardPG.getContent()) {
                this.contents.add(new Content(board.getId(), board.getTitle()));
                System.out.println("게시글 내용물"+contents);
            }
        }

        @Data
        class Content {
            private Integer id;
            private String title;

            public Content(Integer id, String title) {
                this.id = id;
                this.title = title;
            }
        }

    }
}