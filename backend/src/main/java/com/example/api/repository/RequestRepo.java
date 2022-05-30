package com.example.api.repository;

import com.example.api.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepo extends JpaRepository<Request, Long> {

}
