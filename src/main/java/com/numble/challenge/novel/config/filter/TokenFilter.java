package com.numble.challenge.novel.config.filter;

import com.numble.challenge.novel.config.security.AnnoyUser;
import com.numble.challenge.novel.config.security.LoginUser;
import com.numble.challenge.novel.domain.User;
import com.numble.challenge.novel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class TokenFilter extends AbstractAuthenticationProcessingFilter {

    private final UserRepository userRepository;

    public TokenFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                       UserRepository userRepository) {
        super(requiresAuthenticationRequestMatcher);
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        String tokenPayload = request.getHeader("Authorization");
        if (tokenPayload == null) {
            tokenPayload = "unknown user";
        }

        Optional<User> authorizedUser = userRepository.findByEmail(tokenPayload);
        if (!authorizedUser.isPresent()) {
            return new UsernamePasswordAuthenticationToken(new AnnoyUser(), null, Arrays.asList());
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(new LoginUser(authorizedUser.get()), null, Arrays.asList());

        return token;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }


}
