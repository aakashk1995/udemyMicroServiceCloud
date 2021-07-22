package com.service.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.user.entity.UserEntity;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	 UserEntity findByEmail(String email);
	
	
}
