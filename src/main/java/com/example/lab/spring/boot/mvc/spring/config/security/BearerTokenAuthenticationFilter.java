package com.example.lab.spring.boot.mvc.spring.config.security;

import com.example.lab.spring.boot.mvc.spring.config.security.dto.AuthenticatedUser;
import com.example.lab.spring.boot.mvc.spring.config.security.exception.InvalidTokenException;
import com.example.lab.spring.boot.mvc.spring.config.security.service.TokenUserService;
import com.example.lab.spring.boot.mvc.spring.config.security.service.UserAuthorityService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Serial;
import java.util.Collection;

import static com.example.lab.spring.boot.mvc.spring.config.security.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final TokenUserService tokenUserService;
    private final UserAuthorityService userAuthorityService;
    private final AuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.tokenUserService, "A TokenUserService is required");
        Assert.notNull(this.customAuthenticationEntryPoint, "A CustomAuthenticationEntryPoint is required");
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return isAuthenticated() || isNotContainBearerToken(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
            SecurityContextHolder.getContext().setAuthentication(asAuthentication(token));
            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            customAuthenticationEntryPoint.commence(request, response, e);
        }
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * token must be placed in Authorization header with Bearer scheme prefix
     */
    private boolean isNotContainBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !StringUtils.hasText(authorizationHeader)
                || !authorizationHeader.startsWith(BEARER)
                || !StringUtils.hasText(authorizationHeader.substring(7));
    }

    private Authentication asAuthentication(String token) {
        try {
            AuthenticatedUser user = tokenUserService.toUser(token);
            Collection<? extends GrantedAuthority> authorities = userAuthorityService.getAuthorities(user);
            return new TokenAuthenticatedUser(user, token, authorities);
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException(EXPIRED_JWT_TOKEN, "Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException(UNSUPPORTED_JWT_TOKEN, "Unsupported JWT token");
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException(INVALID_JWT_TOKEN, "Invalid JWT token");
        } catch (SecurityException e) {
            throw new InvalidTokenException(INVALID_JWT_SIGNATURE, "JWT signature is invalid");
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException(INVALID_JWT_COMPACT_HANDLER, "JWT token compact of handler is invalid");
        } catch (Exception e) {
            throw new InvalidTokenException(UNEXPECTED_JWT_TOKEN_ERROR, e.getMessage());
        }
    }

    @EqualsAndHashCode(callSuper = true)
    private static class TokenAuthenticatedUser extends AbstractAuthenticationToken {

        @Serial
        private static final long serialVersionUID = -1887189041806242022L;

        private final AuthenticatedUser principal;

        private String credentials;

        private TokenAuthenticatedUser(AuthenticatedUser principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
            super(authorities);
            this.principal = principal;
            this.credentials = credentials;
            super.setAuthenticated(true); // must use super, as we override
        }

        @Override
        public String getCredentials() {
            return this.credentials;
        }

        @Override
        public AuthenticatedUser getPrincipal() {
            return this.principal;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            Assert.isTrue(!isAuthenticated,
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
            super.setAuthenticated(false);
        }

        @Override
        public void eraseCredentials() {
            super.eraseCredentials();
            this.credentials = null;
        }
    }
}
