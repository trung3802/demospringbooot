package vn.vnpt.hdg.api.payload.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;

    public LoginResponse(String accessToken, Integer id, String username) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
    }
}