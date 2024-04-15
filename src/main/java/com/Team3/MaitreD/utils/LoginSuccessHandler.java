package com.Team3.MaitreD.utils;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Team3.MaitreD.models.ApplicationUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        ApplicationUser userDetails = (ApplicationUser) authentication.getPrincipal();
        
        String redirectURL = request.getContextPath();
         
        if (userDetails.hasRole("CUSTOMER")) {
            redirectURL = "/customer/home";
        } else if (userDetails.hasRole("RESTAURANT")) {
            redirectURL = "/restaurant/home";
        } 
        response.sendRedirect(redirectURL);
         
    }
}
