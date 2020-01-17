package com.theOne.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求参数
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //参数中是否存在access-token
        String token = request.getParameter("access-token");
        //判断是否存在
        //不存在，拦截并阻止
        if (StringUtils.isBlank(token)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpServletResponse.SC_FORBIDDEN);
        } /*else {
            //存在则放行 ,可以不写
            ctx.setSendZuulResponse(true);
        }*/
        return null;
    }
}
