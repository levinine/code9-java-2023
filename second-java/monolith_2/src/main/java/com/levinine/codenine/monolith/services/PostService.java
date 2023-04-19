package com.levinine.codenine.monolith.services;

import java.util.List;
import java.util.Optional;

import com.levinine.codenine.monolith.entities.Post;

public interface PostService {

    public List<Post> getAll();

    public Optional<Post> getById(Long id);

}