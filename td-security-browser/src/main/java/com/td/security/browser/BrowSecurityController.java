package com.td.security.browser;

import com.td.security.browser.support.SimpleResponse;
import com.td.security.core.properties.SecurityConstants;
import com.td.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class BrowSecurityController {

    // 获取请求
    private RequestCache requestCache = new HttpSessionRequestCache();
    // 请求跳转
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，条状到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是："+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                this.redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
             }
        }
        return new SimpleResponse("访问的服务器需要身份证认证，请引导用户到登录页");
    }
}
