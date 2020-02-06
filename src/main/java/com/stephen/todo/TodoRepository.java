package com.stephen.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId(Integer userId);
    List<Todo> findByUserIdOrderByDateAsc(Integer userId);
    List<Todo> findByUserIdOrderByNameAsc(Integer userId);
    Todo findByUserIdAndId(Integer userId, Integer Id);
}
