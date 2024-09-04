insert into user_tb(username, password, email, created_at)
values ('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('love', '1234', 'love@nate.com', now());


insert into board_tb(title, content, created_at, user_id)
values ('제목1', '내용1', now(), 1);
insert into board_tb(title, content, created_at, user_id)
values ('제목2', '내용2', now(), 1);
insert into board_tb(title, content, created_at, user_id)
values ('제목3', '내용3', now(), 2);
insert into board_tb(title, content, created_at, user_id)
values ('제목4', '내용4', now(), 2);
insert into board_tb(title, content, created_at, user_id)
values ('제목5', '내용5', now(), 2);

insert into reply_tb(comment, board_id, user_id, created_at) values('댓글1', 5, 1, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글2', 5, 1, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글3', 5, 2, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글4', 4, 2, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글5', 3, 2, now());






