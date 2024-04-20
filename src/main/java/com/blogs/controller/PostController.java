package com.blogs.controller;

import com.blogs.payload.PostDto;
import com.blogs.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
         @RequestParam(name = "pageNo" , defaultValue = "0" ,required = false) int pageNo,
         @RequestParam(name = "pageSize" ,defaultValue = "2" ,required = false) int pageSize,
         @RequestParam(name = "sortBy" , defaultValue = "id" ,required = false) String sortBy,
         @RequestParam(name = "sortDir" ,defaultValue = "asc" , required = false) String sortDir
    ) {
        List<PostDto> postDto = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto>  updatePost(@RequestParam long postId,
                                               @RequestBody PostDto postDto)
    {
      PostDto dto =   postService.updatePost(postId, postDto);
       return  new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam long id){
        postService.deletePost(id);
      return new  ResponseEntity<>("Post Is Deleted !!",HttpStatus.OK);
    }

}
