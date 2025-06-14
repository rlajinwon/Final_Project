package com.spring.app.board.comment;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
    /** PK */
    private Long commentNum;
    /** 작성자(회원ID) */
    private String userName;
    /** 게시글 번호(FK) */
    private Long boardNum;
    /** 댓글 내용 */
    private String commentContents;
    /** 작성일시 */
    private Timestamp commentDate;
}
