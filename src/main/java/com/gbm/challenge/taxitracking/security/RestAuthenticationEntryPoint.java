package com.gbm.challenge.taxitracking.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Authentication entry point.
 *
 * Enable the authentication by a request to the correct URI ( /login ). If the
 * user is not authenticated all requests should fail with the 401 UNAUTHORIZED
 * status code.
 *
 * @author danizz
 */
@Component
public final class RestAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
