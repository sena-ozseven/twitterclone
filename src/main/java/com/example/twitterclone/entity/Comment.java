package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Table(name = "comments", schema = "public")
//Like'tan farklı olarak, bir kullanıcının aynı tweete birden fazla yorum yapabilmesine izin verildiği için burada bir UniqueConstraint kullanılmadı.

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of ="id")
//ara tablo (like ile benzer)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false, length = 280)
    @NotNull
    @NotEmpty
    @NotBlank(message = "Comment content cannot be blank.")
    @Size(max = 280, message = "Comment content cannot exceed 280 characters.")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tweet_id", nullable = false)
    @ToString.Exclude
    private Tweet tweet;


}
