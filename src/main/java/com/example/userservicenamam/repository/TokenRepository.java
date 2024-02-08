package com.example.userservicenamam.repository;

import com.example.userservicenamam.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByValueAndDeleted(String value ,boolean isDeleted);
}
