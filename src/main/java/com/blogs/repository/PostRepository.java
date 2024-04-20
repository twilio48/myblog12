package com.blogs.repository;

import com.blogs.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findUserById(long id);


  //  Post findByPostId(long postId);
}
