package doit.now.main.web;

import doit.now.config.aspect.NoBizLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@Api(value="NowMainController", description="메인 컨트롤러")
public class NowMainController
{

    /**
     * 
     * @return
     */
	@NoBizLog
    @ApiOperation(value = "메인 페이지", notes="메인 페이지로 이동한다")
    @GetMapping("/main")
    public String moveTwbMain(Model model) throws Exception
    {
        log.debug("moveMain");
        return "main/main";
    }

}
	

