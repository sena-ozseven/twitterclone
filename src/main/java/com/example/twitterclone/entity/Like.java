package com.example.twitterclone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
//joint column? -- Bir kullanıcının birden çok tweet'i beğenebilmesi ve bir tweet'in birden çok kullanıcı tarafından beğenilebilmesi, bir @ManyToMany (Çoktan-Çoğa) ilişki gerektiriyordu. Bu ilişkiyi yönetmenin en temiz yolu, bir ara tablo (likes) ve bu tabloyu temsil eden bir Like entity'si oluşturmaktı.
@Table(name = "likes", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "tweet_id"})
})
//@UniqueConstraints --> bir kullanıcının aynı tweet'i birden fazla kez beğenmesi veritabanı seviyesinde engellendi.
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;
}
