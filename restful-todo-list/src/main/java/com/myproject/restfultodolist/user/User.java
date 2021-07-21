package com.myproject.restfultodolist.user;

import com.myproject.restfultodolist.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private Date joindate; // shit! db랑 대소문자까지 맞춰줘야 한다

    // mappedBy = "user"면 user를 갖고 있는 Post가 user(FK)의 주인
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>(); // post에 user id 넣어주면 되나...?
}
