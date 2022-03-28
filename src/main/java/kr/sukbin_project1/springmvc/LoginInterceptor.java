package kr.sukbin_project1.springmvc;

import kr.sukbin_project1.HttpUtils;
import kr.sukbin_project1.dao.Player;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Player player = (Player) session.getAttribute("PLAYER");

        if (player != null) // 로그인 했으면 그대로 진행
            return true;

        // 로그인 안했으면 로그인 화면으로
        String returnUrl = HttpUtils.getRequestURLWithQueryString(request);
        String loginUrl = String
                .format("%s/app/springmvc/v2/player/loginForm?returnUrl=%s",
                        request.getContextPath(),
                        URLEncoder.encode(returnUrl, Charset.defaultCharset()));
        response.sendRedirect(loginUrl);
        return false;
    }
}
