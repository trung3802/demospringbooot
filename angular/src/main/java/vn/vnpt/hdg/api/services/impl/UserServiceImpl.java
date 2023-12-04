package vn.vnpt.hdg.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.dao.base.DaoFactory;
import vn.vnpt.hdg.api.models.AccessToken;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.request.LoginRequest;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.LoginResponse;
import vn.vnpt.hdg.api.repository.AccessTokenRepository;
import vn.vnpt.hdg.api.repository.NguoiDungRepository;
import vn.vnpt.hdg.api.services.UserService;
import vn.vnpt.hdg.api.utils.JwtUtils;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    NguoiDungRepository rNguoiDung;
    
    @Autowired
    AccessTokenRepository rToken;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nd = rNguoiDung.getByTenDangNhap(username);

        return UserDetailsImpl.build(nd);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppResponse signIn(LoginRequest request) {
        AuthenticationManager authenticationManager = appContext.getBean(AuthenticationManager.class);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        rToken.save(new AccessToken(
                userDetails.getUsername(),
                accessToken,
                jwtUtils.getIssuedAt(accessToken),
                jwtUtils.getExpiration(accessToken)
        ));

        return new AppResponse(new LoginResponse(
                accessToken,
                userDetails.getId(),
                userDetails.getUsername()
        ));
    }

    @Override
    public boolean authorized(String username, String path) {
        return "/auth".equalsIgnoreCase(path) || "/test/all".equalsIgnoreCase(path);
    }

	@Override
	public UserDetails checkUserWithToken(String token, String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse getMe(String token) {
		try {
			NguoiDung nd = DaoFactory.getNguoiDungDao().getNguoiDungTheoToken(token);
			if(ObjectUtils.isEmpty(nd)) {
				return new AppResponse("Không tìm thấy thông tin người dùng");
			}
			
			return new AppResponse(nd);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppResponse("Lỗi không xác định: "+ e.getMessage());
		}
	}

	@Override
	public AppResponse signOut(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExpired(String token) {
		// TODO Auto-generated method stub
		return false;
	}
}
