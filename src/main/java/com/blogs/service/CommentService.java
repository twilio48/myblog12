package com.blogs.service;

import com.blogs.payload.CommentDto;

import java.util.List;

public interface CommentService {
public CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

 //   List<CommentDto> getAllComment();

    CommentDto updateComment(long commentId, CommentDto commentDto);

    List<CommentDto> getAllComment(int pageNo, int pageSize, String sortBy, String sortDir);

 List<CommentDto> updateCommentByPostId(long postId, CommentDto commentDto);

    // CommentDto updateCommentByPostId(long postId, CommentDto commentDto);
}

