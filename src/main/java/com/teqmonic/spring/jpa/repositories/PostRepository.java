package com.teqmonic.spring.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teqmonic.spring.jpa.entity.PostEntity;
import com.teqmonic.spring.model.PostProjection;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

	@Query("Select p.name AS name, COUNT(c) AS commentsCount FROM PostEntity p JOIN p.comments c GROUP BY p.name")
	List<PostProjection> findPostWithCommentsCount();
	
	Optional<PostEntity> findByName(String name);
}
