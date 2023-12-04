package vn.vnpt.hdg.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "access_token")
@NoArgsConstructor
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String accessToken;
    private Date issuedAt;
    private Date expiredAt;
    private String appCode;
    private String clientInfo;

    public AccessToken(String username, String accessToken, Date issuedAt, Date expiredAt) {
        this.username = username;
        this.accessToken = accessToken;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }
}
