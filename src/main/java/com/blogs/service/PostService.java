package com.blogs.service;

import com.blogs.payload.PostDto;

import java.util.List;

public interface PostService {

  public  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPosts(int pageNo, int pageName, String sortBy,String sortDir);

  PostDto updatePost(long postId, PostDto postDto);

  void deletePost(long id);
}
