package it.dev.app.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class CustomAfterSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;
      //  log.info("#################################################");
      //  log.info("request="+request.getRequestURI());
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
      //      log.info("Head request: "+name+" = "+request.getHeader(name));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        for (String name:response.getHeaderNames()){
       //     log.info("Head response: "+name+" = "+httpServletResponse.getHeader(name));
        }
    }

}
