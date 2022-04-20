package com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import com.example.demo.models.Joke;
import org.springframework.data.repository.CrudRepository;
//C(create)R(read)U(update)D(delete)

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long>{
    
}
