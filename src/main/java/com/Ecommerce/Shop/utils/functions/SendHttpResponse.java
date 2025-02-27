package com.Ecommerce.Shop.utils.functions;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.Ecommerce.Shop.utils.results.ErrorResult;
import java.io.IOException;


@RequiredArgsConstructor
public class SendHttpResponse {
    private final ObjectMapper objectMapper;




    public void Run(HttpServletResponse response, Integer httpStatus, String message) throws IOException {
        response.setStatus(httpStatus);
        response.setContentType("application/json");


        if (!message.isEmpty())
            response.getWriter().write(objectMapper.writeValueAsString(
                    new ErrorResult(message))
            );
        }

        public void Run(HttpServletResponse response, Integer httpStatus) throws IOException {
            response.setStatus(httpStatus);
            response.setContentType("application/json");
        }
    }


