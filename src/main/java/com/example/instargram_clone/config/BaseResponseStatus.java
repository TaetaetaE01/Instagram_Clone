package com.example.instargram_clone.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS("요청에 성공하였습니다."),

    // GET METHOD
    GET_MEMBERS_NOT_EXISTS_INFO("멤버정보가 존재하지 않습니다."),
    GET_POSTS_NOT_EXISTS_INFO("게시글 정보가 존재하지 않습니다"),
    GET_COMMENTS_NOT_EXISTS_INFO("댓글 정보가 존재하지 않습니다"),
    GET_REPLIES_NOT_EXISTS_INFO("답글 정보가 존재하지 않습니다"),
    GET_LIKES_NOT_EXISTS_INFO("좋아요가 존재하지 않습니다"),

    // POST METHOD
    POST_EMAIL_NOT_EXISTS_INFO("해당 이메일이 존재하지 않습니다."),
    POST_PASSWORD_NOT_MATCH("해당 비밀번호가 일치하지 않습니다"),
    POST_FOLLOW_INFO_EXISTS("이미 팔로우 정보가 존재합니다."),
    POST_MEMBER_INFO_EXISTS("이미 멤버 정보가 존재합니다.");



    private final String message;

    BaseResponseStatus(String message) {
        this.message = message;
    }


}
