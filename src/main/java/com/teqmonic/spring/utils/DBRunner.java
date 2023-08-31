/**
 * 
 */
package com.teqmonic.spring.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.teqmonic.spring.jpa.entity.CommentEntity;
import com.teqmonic.spring.jpa.entity.PostEntity;
import com.teqmonic.spring.jpa.repositories.PostRepository;

public class DBRunner implements CommandLineRunner {
	
	private static int TOTALCOUNT = 1000;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start of bulk record insert.");
		
		List<PostEntity> postEntityList = new ArrayList<>();
		
		for (int index = 0; index < TOTALCOUNT; index++) {
			PostEntity postEntity = PostEntity.builder().name("Post " + index).content("Sample post " + index + " in Twitter").createdDate(LocalDateTime.now()).build();
			postEntity.setComments(Arrays.asList(CommentEntity.builder().review("Sample review " + index)
					.status(Status.APPROVED).createdDateTime(LocalDateTime.now()).post(postEntity).build()));
			postEntityList.add(postEntity);
		}
		postRepository.saveAll(postEntityList);
		
		System.out.println("Bulk record insert is completed.");
	}

}
