package com.eatit.eatitapiv3.exception.dto;

public record ErrorResponse(
        int status,
        String message
) {
}
