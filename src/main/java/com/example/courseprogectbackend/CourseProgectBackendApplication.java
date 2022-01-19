package com.example.courseprogectbackend;

import com.example.courseprogectbackend.enumeration.RoleStatus;
import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.model.Like;
import com.example.courseprogectbackend.model.User;
import com.example.courseprogectbackend.service.implementation.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParser;

@SpringBootApplication
public class CourseProgectBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run( CourseProgectBackendApplication.class,args );
    }
}
