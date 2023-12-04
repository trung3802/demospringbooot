package vn.vnpt.hdg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.vnpt.hdg.api.services.UserService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService sUser;

    @PostMapping("/me")
    public ResponseEntity<?> getMe(HttpServletRequest request) {
        return ResponseEntity.ok(sUser.getMe(getToken(request)));
    }
}
