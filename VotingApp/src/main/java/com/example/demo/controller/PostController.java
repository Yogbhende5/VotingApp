package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.repo.PostRepo;

@RestController
@RequestMapping("post")
public class PostController {
	
	@Autowired
	PostRepo postRepo;
	
	@PostMapping("/add")
	public Post addPost(@RequestBody Post post)
	{
		try {
			Post dBPost=postRepo.save(post);
			return dBPost;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/addListOfPosts")
	public boolean addListOfPosts(@RequestBody List<Post> posts)
	{
		try {
			for(Post p:posts)
			{
				postRepo.save(p);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
