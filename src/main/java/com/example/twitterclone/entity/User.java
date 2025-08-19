package com.example.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "users", schema = "public")
@Entity
@NoArgsConstructor
@AllArgsConstructor

//? alternatively -@EqualsAndHashCode(of = "id")- can be used. in that case methods should not be written.
@Getter
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
