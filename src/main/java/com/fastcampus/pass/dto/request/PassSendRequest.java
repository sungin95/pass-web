package com.fastcampus.pass.dto.request;

public record PassSendRequest(String sellectedUserId) {
    public static PassSendRequest of(String sellectedUserId) {
        return new PassSendRequest((sellectedUserId));
    }


}
