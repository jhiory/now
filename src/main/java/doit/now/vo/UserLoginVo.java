package doit.now.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userlogin")
public class UserLoginVo {
    public String userid;
    public String pwd;
    public String pwdfailct;
    public String lastpwdupddttm;
    public String lastloginsuccessdttm;
    public int loginct;
    public String etcmeta1;
    public String etcmeta2;
    public String etcmeta3;
    public boolean ismatched;
    public String reguser;
    public String regdttm;
    public String moduser;
    public String moddttm;
}
