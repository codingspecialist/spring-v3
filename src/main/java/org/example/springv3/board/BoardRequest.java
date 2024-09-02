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
        @NotEmpty(message = "비워놓지마. (근데 이거 안써줘도 됨)")
        private String content;

        // insert 할 때는 toEntity 를 만든다.
        public Board toEntity(User sessionUser) { // 날짜는 엔티티에 @CreationTimeStamp 붙여주면 자동으로 들어간다.
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build(); // shift + enter
        }
    }

}
