package com.qxc.gacspboot.controller.Intercepter;

import com.qxc.gacspboot.controller.AuthorNeedAnnotion.AuthorNeed;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        log.info(method.getName()+"开始执行");
        AuthorNeed authorNeed=method.getDeclaredAnnotation(AuthorNeed.class);

        if(authorNeed!=null&& authorNeed.value()){
            try{
                Cookie[] cookies=request.getCookies();
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("token")){
                        return true;
                    }
                }
                request.getRequestDispatcher(request.getContextPath()+"/login.html").forward(request,response);
            }catch (Exception ex){
                request.getRequestDispatcher(request.getContextPath()+"/login.html").forward(request,response);
            }
            return false;
        }
        return true;
    }
}
