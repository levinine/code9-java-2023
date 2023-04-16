package com.levinine.codenine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levinine.codenine.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
