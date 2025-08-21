package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "tweets", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tweet {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false, length = 280) //length --> data layer -for hibernate to validate
    @NotEmpty
    @NotNull
    @NotBlank(message = "Tweet cannot be blank.")
    @Size(max = 280, message = "Tweet cannot exceed 280 characters.") //size --> controller layer
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Like> likes = new HashSet<>();

    //-----RETWEET-----
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_tweet_id")
    @ToString.Exclude
    private Tweet originalTweet;

    @Override             //polymorphism
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        //downcast
        Tweet tweet = (Tweet) obj;
        return tweet.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
