package com.cardenascode.reactive.router;

import com.cardenascode.reactive.handler.StudentHandler;
import com.cardenascode.reactive.handler.StudentStreamHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class Router {


    private final StudentHandler handler;


    private final StudentStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse>routerFunction(){
        return RouterFunctions.route()
                .GET("/get/students",handler::getAllStudents)
                .GET("/get/students/stream",streamHandler::getAllStudents)
                .GET("/get/students/{studentId}}",handler::findById)
                .POST("/add/students/{studentId}}",handler::addStudent)
                .build();
    }
}
