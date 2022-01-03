package com.example.demo.dataaccess;

import com.example.demo.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;  

public interface IBookRepository extends JpaRepository<Book, Integer> {

}
