package com.david.agendadortarefas.infrastructure.exceprions;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
