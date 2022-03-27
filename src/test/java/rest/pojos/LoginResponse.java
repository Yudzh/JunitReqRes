package rest.pojos;

import lombok.Data;

@Data
public class LoginResponse {
    private int id;
    private String token;
}
