package org.example.springv3.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.springv3.core.error.ex.Exception401;
import org.example.springv3.user.User;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //User sessionUser = (User) session.getAttribute("sessionUser");

//        if (sessionUser == null) {
//            throw new Exception401("인증되지 않았어요");
//        }

        User sessionUser = User.builder()
                .id(1)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();

        session.setAttribute("sessionUser", sessionUser);

        return true; // false면 컨트롤러 진입안됨
    }
}
