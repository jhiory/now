package com.hcteleweb.teleweb.main.kotis.web;

import com.hcteleweb.teleweb.config.aspect.NoBizLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcteleweb.teleweb.config.properties.TelewebPropertyConfig;
import com.hcteleweb.teleweb.core.util.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@Api(value="TwbMainController", description="텔레웹 메인 컨트롤러")
public class TelewebMainController
{
    private final TelewebPropertyConfig telewebPropertyConfig;
    
    /**
     * 
     * @return
     */
	@NoBizLog
    @ApiOperation(value = "텔레웹 메인 페이지", notes="텔레웹 메인 페이지로 이동한다")
    @GetMapping("/main/kotis/web/TwbMain")
    public String moveTwbMain(Model model) throws Exception
    {
        final String mainTwbPage = telewebPropertyConfig.getThymleafMainPath();
        
        log.debug("moveTwbMain ::: {}", mainTwbPage);
        
        final boolean useAsp = telewebPropertyConfig.isUseAsp();
        model.addAttribute("useAsp", useAsp);
        model.addAttribute("timestamp", DateUtils.toEpochMilli());
        return mainTwbPage;
    }
}
	

