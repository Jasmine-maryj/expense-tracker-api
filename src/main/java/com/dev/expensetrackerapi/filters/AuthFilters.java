package com.dev.expensetrackerapi.filters;

import com.dev.expensetrackerapi.config.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilters extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null){
            String[] authHeaderArr = authHeader.split("Bearer");
            if(authHeaderArr.length > 1 && authHeaderArr[1] != null){
                String token = authHeaderArr[1];
                try{
                    //fetch the fields from request body
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();

                    httpServletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));

                }catch (Exception e){
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid or expired token");
                    return;
                }
            }else{
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
            }
        }else{
            httpServletResponse.sendError(HttpStatus.FAILED_DEPENDENCY.value(), "Authorization must be provided");
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
