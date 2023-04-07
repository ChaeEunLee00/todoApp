package com.codestates.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Optional<Todo> findByTodoOrder(Integer todoOrder);
}
