package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Joke;

import org.springframework.stereotype.Service;

@Service
public class JokeService {
    public void saveJoke(){
        // INSERT INTO joke (text) VALUES('XXXXXX')
        save()
    }

    public ArrayList<Joke> getAllJokes(){
        // SELECT * FROM joke
        findAll()
        return null;
    }
}