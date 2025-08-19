package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @NotBlank(message = "Tweet cannot be blank.")
    @Size(max = 280, message = "Tweet cannot exceed 280 characters.") //size --> controller layer
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
