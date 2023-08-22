package com.eatit.eatitapiv3.exception;

import com.eatit.eatitapiv3.exception.dto.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class EatItException extends ResponseStatusException {
    public EatItException(final HttpStatusCode status) {
        super(status);
    }

    public EatItException(final HttpStatusCode status, final String reason) {
        super(status, reason);
    }

    public EatItException(final ErrorCode errorCode, final String reason, final Throwable cause) {
        super(errorCode.getStatus(), reason, cause);
    }

    public EatItException(final HttpStatusCode status, final String reason, final Throwable cause) {
        super(status, reason, cause);
    }

    protected EatItException(final HttpStatusCode status, final String reason, final Throwable cause, final String messageDetailCode, final Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
