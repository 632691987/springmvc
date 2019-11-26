package org.vincent.springmvc.controller.springpure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vincent.springmvc.system.SystemConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping(SystemConstant.FE_SPRING_PURE + "session")
public class SessionStudyController {

    String sessionKey = "key1";

    private String SESSION_KEY = "cookie_key";

    private Map<String, String> redisStore = new ConcurrentHashMap<>();

    @GetMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            int value = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies == null || cookies.length == 0) {
                createCookie(request, response);
            } else {
                Optional<Cookie> cookieOptional = Arrays.stream(cookies).filter(cookie -> cookie.getName().equalsIgnoreCase(SESSION_KEY)).findAny();
                if (cookieOptional.isPresent()) {
                    Cookie cookie = cookieOptional.get();
                    if (redisStore.get(cookie.getValue()) == null) {
                        createCookie(request, response);
                    } else {
                        value = Integer.parseInt(redisStore.get(cookie.getValue()));
                        value++;
                        redisStore.put(cookie.getValue(), String.valueOf(value));
                    }
                } else {
                    createCookie(request, response);
                }
            }

            model.addAttribute(sessionKey, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SystemConstant.FE_SPRING_PURE + "session/index";
    }

    private void createCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cookie cookie = new Cookie(SESSION_KEY, session.getId());
        redisStore.put(session.getId(), Integer.toString(0));
        response.addCookie(cookie);
    }

}
