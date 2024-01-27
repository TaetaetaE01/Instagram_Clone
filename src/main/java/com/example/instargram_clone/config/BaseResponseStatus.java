package com.example.instargram_clone.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS("요청에 성공하였습니다."),
    GET_MEMBERS_NOT_EXISTS_INFO("멤버정보가 존재하지 않습니다."),
    GET_POSTS_NOT_EXISTS_INFO("게시글 정보가 존재하지 않습니다"),
    GET_COMMENTS_NOT_EXISTS_INFO("댓글 정보가 존재하지 않습니다"),
    GET_REPLIES_NOT_EXISTS_INFO("답글 정보가 존재하지 않습니다"),
    GET_LIKES_NOT_EXISTS_INFO("좋아요가 존재하지 않습니다");


    private final String message;

    BaseResponseStatus(String message) {
        this.message = message;
    }


}
