package vn.vnpt.hdg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import vn.vnpt.hdg.api.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    JwtUtils jwtUtils;

    protected String getToken(HttpServletRequest request) {
        return jwtUtils.getToken(request);
    }
}
