package com.myproject.restfultodolist.post;

import com.myproject.restfultodolist.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/users/posts")
    public List<Post> findAllPosts(){
        return postService.findAllPosts();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> findPostsOfOneUser(@PathVariable int id){
        return postService.findPostsOfOneUser(id);
    }
}
