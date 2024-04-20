package com.blogs.service.impl;


import com.blogs.entity.Comment;
import com.blogs.entity.Post;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payload.CommentDto;
import com.blogs.repository.CommentRepository;
import com.blogs.repository.PostRepository;
import com.blogs.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post is not found for this postId :" + postId)
        );

        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);

        Comment saved = commentRepository.save(comment);

        return mapToDto(saved);

    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment is not exist for this Id" + commentId)
        );
        commentRepository.deleteById(commentId);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
       List<Comment> comment = commentRepository.findByPostId(postId);
        List<CommentDto> commentDto = comment.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return commentDto;
    }

//    @Override
//    public List<CommentDto> getAllComment() {
//        List<Comment> allComments = commentRepository.findAll();
//        List<CommentDto> commentDto = allComments.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
//        return commentDto;
  //  }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment is not found for this Id :" + commentId)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        //comment.setPost(comment);

        Comment saved = commentRepository.save(comment);
        return mapToDto(saved);
    }

    @Override
    public List<CommentDto> getAllComment(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Comment> pageComments = commentRepository.findAll(pageable);
        List<Comment> comment = pageComments.getContent();
        List<CommentDto> commentDto = comment.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return commentDto;
    }

    @Override
    public List<CommentDto> updateCommentByPostId(long postId, CommentDto commentDto) {
        List<Comment> byPostId = commentRepository.findByPostId(postId);
        Comment comment = new Comment();
         comment.setName(commentDto.getName());
         comment.setEmail(commentDto.getEmail());
         comment.setBody(commentDto.getBody());
         comment.setPost((Post) byPostId);
        Comment saved = commentRepository.save(comment);

        return (List<CommentDto>) mapToDto(saved);
    }


    CommentDto  mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        return commentDto;
}
}
