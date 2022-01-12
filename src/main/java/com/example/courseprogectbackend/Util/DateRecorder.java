package com.example.courseprogectbackend.Util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateRecorder {

    public LocalDateTime currentTime(){
        return LocalDateTime.now();
    }
}
