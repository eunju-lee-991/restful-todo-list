package com.myproject.restfultodolist.user;

import com.myproject.restfultodolist.Exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public ResponseEntity<CollectionModel<EntityModel<User>>> findAllUsers() {
        List<EntityModel<User>> result = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for(User user : users){
            EntityModel model = EntityModel.of(user);
            model.add(linkTo(methodOn(this.getClass()).findAllUsers()).withSelfRel());

            result.add(model);
        }

        return ResponseEntity.ok(CollectionModel.of(result, linkTo(methodOn(this.getClass()).findAllUsers()).withSelfRel()));
    }

    // ctrl + alt + v 하면 return에 있던 거 변수로 만들어줌
    public ResponseEntity findOneUser(int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID %s not found", id));
        }

        EntityModel model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllUsers());
        model.add(linkTo.withRel("all-users"));

        return ResponseEntity.ok(model);

        // *** optinal and exception
        // return userRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Transactional
    public ResponseEntity<User> createUser(User user){
        User savedUser =  userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    public ResponseEntity<User> updateUser(int id, User newUser){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID %s not found", id));
        }

        User storedUser = user.get();
        storedUser.setName(newUser.getName());
        storedUser.setPassword(newUser.getPassword()); // *** 기존 회원정보에서 이름만 수정했을 때 기존 비번은 어떻게 넘겨주지?

        User updatedUser = userRepository.save(storedUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    public void deleteUser(int id){
        Optional<User> user = userRepository.findById(id);
        User storedUser = user.get();

        userRepository.delete(storedUser);
    }
}
