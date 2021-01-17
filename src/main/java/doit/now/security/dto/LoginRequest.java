package doit.now.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("username=");
        buffer.append(username);
        buffer.append(", password=");
//        buffer.append("****");
        buffer.append(password);


        return buffer.toString();
    }
}
