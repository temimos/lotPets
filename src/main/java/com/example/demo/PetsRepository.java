package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface PetsRepository extends CrudRepository<Pet, Long> {

}
