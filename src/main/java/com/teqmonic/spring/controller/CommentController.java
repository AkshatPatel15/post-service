/**
 * 
 */
package com.teqmonic.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teqmonic.spring.model.Comment;
import com.teqmonic.spring.service.CommentService;

/**
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/comment")
	public ResponseEntity<List<Comment>> getComments() {
		List<Comment> comments = commentService.getComments();
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

}
