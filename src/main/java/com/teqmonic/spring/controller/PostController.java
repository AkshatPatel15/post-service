package com.teqmonic.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teqmonic.spring.jpa.model.Post;
import com.teqmonic.spring.service.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/post")
	public ResponseEntity<Long> savePost(@RequestBody Post post) {
		long postId = postService.savePosts(post);
		return new ResponseEntity<>(postId, HttpStatus.CREATED);
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getPost(@PathVariable long id) {
		Post post = postService.getPost(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable long id) {
		postService.deletePost(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/post/{id}")
	public ResponseEntity<Long> savePost(@RequestBody Post post, @PathVariable long id) {
		if (postService.updatePost(id, post)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


}
