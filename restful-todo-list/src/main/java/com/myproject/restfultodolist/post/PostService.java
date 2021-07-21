package com.myproject.restfultodolist.post;

import com.myproject.restfultodolist.Exception.PostNotFoundException;
import com.myproject.restfultodolist.Exception.UserNotFoundException;
import com.myproject.restfultodolist.user.User;
import com.myproject.restfultodolist.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public List<Post> findAllPosts(){
        // *** EntityModel

        return postRepository.findAll();
    }

    public List<Post> findPostsOfOneUser(int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // *** 이쁘게 정리 & EntityModel
        if(user.get().getPosts().size() < 1){
            throw new PostNotFoundException("해당 사용자의 등록된 글이 없습니다.");
        }else {
            return user.get().getPosts();
        }
    }
}
