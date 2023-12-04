package vn.vnpt.hdg.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import vn.vnpt.hdg.api.payload.response.AppResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        String message = authException.getMessage();
        if (authException instanceof InsufficientAuthenticationException) {
            message = "Token không hợp lệ hoặc đã hết hạn.";
        }

        AppResponse appResponse = new AppResponse(HttpServletResponse.SC_UNAUTHORIZED, message);
        appResponse.generateErrRes(response);
    }
}
