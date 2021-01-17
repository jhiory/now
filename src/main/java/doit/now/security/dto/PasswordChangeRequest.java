package doit.now.security.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    public String mngId;
    public String prePwd;
    public String mngPwd;
    public String mngPwdCheck;
    public String regUser;
    public String modUser;
}
