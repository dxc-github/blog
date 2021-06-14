package top.putongren.dxcblog.web.frotend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FIndexController {
    @GetMapping("/index")
    public String toindex(){
        return "frontend/index";
    }

    @GetMapping("/head")
    public String tohead(){
        return "frontend/head";
    }
}
