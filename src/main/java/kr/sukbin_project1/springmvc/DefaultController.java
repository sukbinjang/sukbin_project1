package kr.sukbin_project1.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    /**
     * 다른 매핑이 없는 URI 요청이 왔을 경우에 이 메서드에 매핑된다.
     * /springmvc/v2/user/userForm, /springmvc/v2/user/loginForm,
     * /springmvc/v2/user/myInfo 등은 명시적 매핑이 없으므로 여기에 매핑된다.
     */
    @GetMapping("/**")
    public void mapDefault() {
    }
}