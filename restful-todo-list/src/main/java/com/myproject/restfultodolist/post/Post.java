package com.myproject.restfultodolist.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.restfultodolist.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer post_id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private Date registrationDate;

    //@JoinColumn(name = "USER_ID")
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
