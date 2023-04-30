package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	@Query(value = "select count(?1) from User where username like '?1'",nativeQuery = true)
	int count(String username);
	User findByUsername(String username);
	
	@Query(value="select u.username as username,p.title as title,p.content as content from user u join user_posts up on u.id=up.user_id join post p on up.posts_p_id=p.p_id where u.username=?1",nativeQuery = true)
	UserDto userPostDetails(String username);
}
