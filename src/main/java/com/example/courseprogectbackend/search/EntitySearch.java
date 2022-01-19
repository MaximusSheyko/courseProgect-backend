package com.example.courseprogectbackend.search;

import lombok.NonNull;

import java.util.List;

public interface EntitySearch<T> {
    List<T> search(String text,@NonNull String...fields);
}
