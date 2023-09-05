/**
 * 
 */
package com.teqmonic.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.teqmonic.spring.jpa.entity.CommentEntity;
import com.teqmonic.spring.model.Comment;
import com.teqmonic.spring.utils.Status;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * 
 */
@Component
public class CommentService {

	@Autowired
	private EntityManager entityManager;
	
	@Value("${post-service.teqmonic.comment.fetchsize}")
	private int commentsThreshold;

	/**
	 * Retrieves Comment object with the size as per configuration
	 * @return
	 */
	public List<Comment> getComments() {

		Query getCommentsQuery = entityManager.createQuery("select c from CommentEntity c where status IN (:status)");

		getCommentsQuery.setParameter("status", Status.APPROVED);
		getCommentsQuery.setMaxResults(commentsThreshold);
		//getCommentsQuery.setFirstResult(0);
		
		@SuppressWarnings("unchecked")
		List<CommentEntity> comments = getCommentsQuery.getResultList();
		List<Comment> commentList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(comments)) {
			comments.stream().forEach(commentEntity -> {
				commentList.add(Comment.builder().review(commentEntity.getReview()).status(commentEntity.getStatus())
						.createdDateTime(commentEntity.getCreatedDateTime()).build());
			});	
		}
		return commentList;
	}
}
