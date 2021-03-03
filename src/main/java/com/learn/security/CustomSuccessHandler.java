package com.learn.security;

import com.learn.constant.SystemConstant;
import com.learn.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";
        //if role is ADMIN so redirect to controller : /admin
        //if role is USERER so redirect to controller :/trang-chu
        List<String> roles = SecurityUtils.getAuthorities();
        if(isAdmin(roles)){
            url = "/admin";
        }else if(isAdmin(roles)){
            url = "/trang-chu";
        }
        return url;
    }

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    private boolean isAdmin(List<String> listRole){
        if (listRole.contains("ADMIN")){
            return true;
        }
        return false;
    }
    private boolean isUser(List<String> listRole){
        if (listRole.contains("USER")){
            return true;
        }
        return false;
    }
}
