package com.code9.springsecurity.repository;


import com.code9.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 Repository class for managing {@link User} entities in the database.
 Provides methods for common CRUD operations as well as custom queries.
 This interface extends the {@link JpaRepository} interface, which provides
 basic database operations out of the box, including save, update, delete, and
 findById methods.
 The implementation of this interface is generated at runtime by Spring Data JPA,
 based on the entity class and the methods defined in this interface.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
