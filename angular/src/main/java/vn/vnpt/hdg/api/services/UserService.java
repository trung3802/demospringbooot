package vn.vnpt.hdg.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import vn.vnpt.hdg.api.payload.request.LoginRequest;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface UserService {
	
	@Transactional
    UserDetails checkUserWithToken(String token, String username) throws UsernameNotFoundException;
	
    AppResponse signIn(LoginRequest request);

    boolean authorized(String username, String path);
    
    AppResponse getMe(String token);
    
    AppResponse signOut(String token);

    boolean isExpired(String token);
}