package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Table(name = "users", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter   //? alternatively -@EqualsAndHashCode(of = "id")- can be used. in that case methods should not be written.
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @NotBlank(message = "Username cannot be blank")
    @Size(max=50)
    private String username;


    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 255)
    @Email(message = "Please provide a valid email address") // validation
    private String email;

    @Column(name="password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(max=255)
    @ToString.Exclude
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets = new ArrayList<>();

    //List/Set yazdıgımızda add ve remove methodlarını da yazmamız gerekiyor:
    public void addTweet(Tweet tweet) {
        if (tweet == null) {
            throw new IllegalArgumentException("Tweet cannot be null");
        }
        if(tweet.getUser().equals(this)) {
            tweets.add(tweet);
        }
    }

    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
    }

    public List<Tweet> getTweets() {
        return Collections.unmodifiableList(this.tweets);
    }

    @Override            //polymorphism
    public boolean equals(Object obj) {
        if (obj == this) {
             return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        //downcast
        User user = (User) obj;
        return user.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
