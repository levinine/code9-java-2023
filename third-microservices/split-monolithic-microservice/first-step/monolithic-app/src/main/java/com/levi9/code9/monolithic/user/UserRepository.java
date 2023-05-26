package com.levi9.code9.monolithic.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    List<User> findAllByReceiveUpdate(boolean state);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

}
