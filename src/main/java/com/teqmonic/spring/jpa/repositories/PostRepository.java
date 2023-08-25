package com.teqmonic.spring.jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teqmonic.spring.jpa.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

}
