package com.numble.challenge.novel.config.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AnnoymousToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;
    public AnnoymousToken(Object principal, Object credentials,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }


    @Override
    public Object getCredentials() {
        throw new IllegalArgumentException("로그인이 필요합니다.");
    }

    @Override
    public Object getPrincipal() {
        throw new IllegalArgumentException("로그인이 필요합니다.");
    }

}
