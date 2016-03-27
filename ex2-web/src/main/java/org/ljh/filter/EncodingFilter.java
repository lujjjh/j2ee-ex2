package org.ljh.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ljh
 * On 16/3/27.
 */
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
