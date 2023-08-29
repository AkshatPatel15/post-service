package com.teqmonic.spring.service;

import static com.teqmonic.spring.utils.Constants.UTC_ZONE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.teqmonic.spring.jpa.entity.CommentEntity;
import com.teqmonic.spring.jpa.entity.PostEntity;
import com.teqmonic.spring.jpa.repositories.PostRepository;
import com.teqmonic.spring.model.Comment;
import com.teqmonic.spring.model.Post;
import com.teqmonic.spring.model.PostProjection;
import com.teqmonic.spring.utils.Status;




@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	/**
	 * Persists Post object into the database. The method should be Idempotent.
	 * 
	 * @param post
	 * @return
	 */
	@Transactional
	public long savePosts(Post post) {
        // Idempotent check
		Optional<PostEntity> optionalPostEntity = postRepository.findByName(post.getName());
		if (optionalPostEntity.isPresent()) {
			System.out.println("Post entity is already available for the given name: " + optionalPostEntity.get().getName());
			return optionalPostEntity.get().getId();
		}
		
		PostEntity postEntity = new PostEntity();
		postEntity.setContent(post.getContent());
		postEntity.setName(post.getName());
		postEntity.setComments(buildComments(post.getComments(), postEntity));
		postEntity.setCreatedDate(LocalDateTime.now(UTC_ZONE));

		PostEntity postEntityResponse = postRepository.save(postEntity);
        return postEntityResponse.getId();
	}

	/**
	 * Retrieves Post object for the given Post id. Ensure there is only unique
	 * entity is created using Idempotent check.
	 * 
	 * @param id
	 * @return
	 */
	public Post getPost(long id) {
		Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
		
		Post post = new Post();
		if(optionalPostEntity.isPresent()) {
			PostEntity postEntity = optionalPostEntity.get();
			post.setContent(postEntity.getContent());
			post.setCreatedDate(postEntity.getCreatedDate());
			post.setName(postEntity.getName());
			// build Comments
			List<Comment> comments = postEntity.getComments().stream().map(commentEntity -> Comment.builder().review(commentEntity.getReview())
					.createdDateTime(commentEntity.getCreatedDateTime()).status(commentEntity.getStatus()) .build()).toList();
			post.setComments(comments);
		}
		return post;
	}
	
	public Post getPost(String name) {
		Optional<PostEntity> optionalPostEntity = postRepository.findByName(name);
		
		Post post = new Post();
		if(optionalPostEntity.isPresent()) {
			PostEntity postEntity = optionalPostEntity.get();
			post.setContent(postEntity.getContent());
			post.setCreatedDate(postEntity.getCreatedDate());
			post.setName(postEntity.getName());
			// build Comments
			List<Comment> comments = postEntity.getComments().stream().map(commentEntity -> Comment.builder().review(commentEntity.getReview())
					.createdDateTime(commentEntity.getCreatedDateTime()).status(commentEntity.getStatus()) .build()).toList();
			post.setComments(comments);
		}
		return post;
	}
	
	/**
	 * @return
	 */
	public List<PostProjection> getPostWithCommentsCount() {
		return postRepository.findPostWithCommentsCount();
	}
	
	
	/**
	 * Deletes Post object for the given post id
	 * 
	 * @param id
	 */
	public void deletePost(long id) {
		postRepository.deleteById(id);
	}
	
	/**
	 * @param id
	 * @param post
	 * @return
	 */
	@Transactional
	public boolean updatePost(long id, Post post) {
		Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
		if(optionalPostEntity.isPresent()) {
			PostEntity postEntity = optionalPostEntity.get();
			postEntity.setContent(post.getContent());
			postEntity.setCreatedDate(LocalDateTime.now(UTC_ZONE));
			postEntity.setName(post.getName());
			// build Comments
			
			if (!ObjectUtils.isEmpty(post.getComments())) {
				for (CommentEntity entity : postEntity.getComments()) {
					entity.setReview(post.getComments().get(0).getReview());
				}
			}
			// todo: If comments are present in the latest Post request, 
			//       then delete existing comments entity and insert the comments from the request
			postRepository.save(postEntity);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param commentList
	 * @return
	 */
	private static List<CommentEntity> buildComments(List<Comment> commentList, PostEntity postEntity) {
		List<CommentEntity> commentEntityList = new ArrayList<>();
		
		for (Comment comment : commentList) {
			commentEntityList.add(CommentEntity.builder().review(comment.getReview())
					.createdDateTime(LocalDateTime.now(UTC_ZONE)).post(postEntity).status(Status.APPROVED).build());
		}
		
		return commentEntityList;
	}

}
