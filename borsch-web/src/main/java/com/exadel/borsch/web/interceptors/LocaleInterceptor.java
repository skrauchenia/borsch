package com.exadel.borsch.web.interceptors;

import com.exadel.borsch.entiry.User;
import com.exadel.borsch.web.users.UserUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author Andrew Zhilka
 */
public class LocaleInterceptor extends HandlerInterceptorAdapter {
    public LocaleInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {

        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal == null) {
            return true; //skip method body
        }
        User user = UserUtils.getUserByPrincipal(userPrincipal);

        if (user != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }

            localeResolver.setLocale(request, response, user.getLocale());
        }
        // Proceed in any case.
        return true;
    }
}
