    package com.Ecommerce.Shop.configs;


import com.Ecommerce.Shop.utils.constants.Messages;
import com.Ecommerce.Shop.utils.results.ErrorResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;


    @Component("customAuthenticationEntryPoint")

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)throws IOException  {
        var errorResult = new ErrorResult(Messages.unauthorized.toString()); // carpeta utils  en src/main/java/com/Ecommerce/Shop/utils

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        OutputStream responseStream = response.getOutputStream();


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, errorResult);
        responseStream.flush();
    }
}
