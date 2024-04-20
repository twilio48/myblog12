package com.blogs.service.impl;

import com.blogs.entity.Post;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payload.PostDto;
import com.blogs.repository.PostRepository;
import com.blogs.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post postEntity = mapToEntity(postDto);
        Post savedPost = postRepo.save(postEntity);
      return mapToDto(savedPost);

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo , int pageSize , String sortBy , String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo , pageSize,sort);
        Page<Post> pagePosts = postRepo.findAll(pageable);
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post posts = postRepo.findUserById(postId);
        posts.setTitle(postDto.getTitle());
        posts.setDescription(postDto.getDescription());
        posts.setContent(postDto.getContent());
        Post saved = postRepo.save(posts);
        PostDto dto = mapToDto(saved);
        return dto;

    }

    @Override
    public void deletePost(long id) {
        postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post not found with id:"+id));

      postRepo.deleteById(id);
    }

    private Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDto mapToDto( Post post){
        return modelMapper.map(post,PostDto.class);
    }

}
