package com.hpf.zuulserver.Filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PreRequestLogFilter
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  19:19
 * @Version 1.0
 **/
public class PreRequestLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        System.out.print(String.format("send %s request to %s",request.getMethod(),request.getRequestURL()));
        return null;
    }

}
