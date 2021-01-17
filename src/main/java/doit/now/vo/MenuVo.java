package doit.now.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("menu")
public class MenuVo {
    public String pgmid;
    public String upperpgmid;
    public String pgmdspnm;
    public String pgmnm;
    public String pgmurl;
    public String pgmsuburl;
    public String categoryyn;
    public String expoyn;
    public int pgmordr;
    public String pgmtitlnm;
    public String pgmformurl;
    public String pgmty;
    public String pgmstatus;
    public String pgmdc;
    public String extrlaccesstatus;
    public String param;
    public String shortcut;
    public String pgmgrpid;
    public String pgmtaskno;
    public String etcmeta1;
    public String etcmeta2;
    public String useyn;
    public String reguser;
    public String regdttm;
    public String moduser;
    public String moddttm;
}
