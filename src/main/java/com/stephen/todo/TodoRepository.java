package com.stephen.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByUserId(Integer userId);
    public List<Todo> findByUserIdOrderByDateAsc(Integer userId);
    public List<Todo> findByUserIdOrderByNameAsc(Integer userId);
    public List<Todo> findByUserIdAndId(Integer userId, Integer Id);
}
