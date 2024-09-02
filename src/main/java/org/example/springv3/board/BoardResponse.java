package org.example.springv3.board;

import lombok.Data;
import org.example.springv3.user.User;

public class BoardResponse {
    @Data
    public static class DetailDTOV2 {
        private Integer id;
        private String title;
        private String content;
        private Boolean isOwner;
        private UserDTO user;

        @Data
        public class UserDTO {
            private Integer id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

    }
}
