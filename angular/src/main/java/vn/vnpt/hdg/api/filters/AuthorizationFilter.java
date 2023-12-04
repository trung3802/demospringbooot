package vn.vnpt.hdg.api.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vnpt.hdg.api.services.UserService;
import vn.vnpt.hdg.api.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {
	
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserService sUser;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String jwt = jwtUtils.parseJwt(httpRequest);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
//                if (!sUser.authorized(username, httpRequest.getServletPath())) {
//                    HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//                    AppResponse appResponse = new AppResponse(HttpServletResponse.SC_FORBIDDEN, AppError.FORBIDDEN);
//                    appResponse.generateErrRes(httpResponse);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
