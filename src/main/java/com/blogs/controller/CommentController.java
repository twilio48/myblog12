package com.blogs.controller;


import com.blogs.payload.CommentDto;
import com.blogs.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //http://localhost:8080/api/comments?postId=1

   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public  ResponseEntity<CommentDto>   createComment(@RequestParam long postId, @RequestBody CommentDto commentDto){
        CommentDto Dto = commentService.createComment(postId, commentDto);
        return  new ResponseEntity<>(Dto,HttpStatus.CREATED);
    }


//http://localhost:8080/api/comments/15
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("COMMENT IS DELETED",HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId){
        List<CommentDto> dto = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id" ,required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        List<CommentDto> allComment = commentService.getAllComment(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allComment,HttpStatus.OK);
    }

    //http://localhost:8080/api/comments?commentId=9
    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestParam long commentId, @RequestBody CommentDto commentDto){
        CommentDto commentDto1 = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(commentDto1 , HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> updateCommentByPostId(@PathVariable long postId, @RequestBody CommentDto commentDto){
        List<CommentDto> commentDto2 = commentService.updateCommentByPostId(postId, commentDto);
        return new ResponseEntity<>(commentDto2 , HttpStatus.OK);
    }





}

