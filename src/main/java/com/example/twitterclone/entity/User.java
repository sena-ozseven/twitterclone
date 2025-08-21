package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Table(name = "users", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter   // alternative: @EqualsAndHashCode(of = "id")
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @NotEmpty
    @NotNull
    @NotBlank(message = "Username cannot be blank")
    @Size(max=50)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @NotEmpty
    @NotNull
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 255)
    @Email(message = "Please provide a valid email address") // validation
    private String email;

    @Column(name="password", nullable = false)
    @NotEmpty
    @NotNull
    @NotBlank(message = "Password cannot be blank")
    @Size(max=255)
    @ToString.Exclude
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude // StackOverflowError'u önlemek için. (sonsuz döngüye girebilir.)
    @Getter(AccessLevel.NONE) // Lombok getter oluşturmasın diye. -> benim getter'ım ezilmesin diye.
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude // StackOverflowError'u önlemek için
    @Getter(AccessLevel.NONE) // Lombok getter oluşturmasın, kendimiz yazdık
    private Set<Like> likes = new HashSet<>();


    //Bidirectional Synchronization
    public void addTweet(Tweet tweet) {
        if (tweet == null) {
            throw new IllegalArgumentException("Tweet cannot be null");
        }

        //this.tweets.add(tweet);
        //tweet.setUser(this);

        if(tweet.getUser().equals(this)) {
            this.tweets.add(tweet);
            tweet.setUser(this); //--> bidirectional
        }
    }

    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
        tweet.setUser(null);
    }

    public List<Tweet> getTweets() {
        return Collections.unmodifiableList(this.tweets);
    }

    //--------likes
    public void addLike(Like like) {
        if (like == null) {
            throw new IllegalArgumentException("Like cannot be null");
        }
        //this.likes.add(like);
        //like.setUser(this);

        if(like.getUser().equals(this)) {
            this.likes.add(like);
            like.setUser(this);
        }
    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setUser(null);
    }

    public Set<Like> getLikes() {
        return Collections.unmodifiableSet(this.likes);
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
