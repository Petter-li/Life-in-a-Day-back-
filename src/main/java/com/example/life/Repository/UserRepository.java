package com.example.life.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.life.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//通过用户名来查找
	public User findByName(String name);
}
