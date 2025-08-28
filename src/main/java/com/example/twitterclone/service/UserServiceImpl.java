package com.example.twitterclone.service;

import com.example.twitterclone.dto.UserLoginRequestDto;
import com.example.twitterclone.dto.UserRegisterRequestDto;
import com.example.twitterclone.dto.UserResponseDto;
import com.example.twitterclone.dto.UserUpdateRequestDto;
import com.example.twitterclone.entity.User;
import com.example.twitterclone.exception.InvalidCredentialsException;
import com.example.twitterclone.exception.ResourceNotFoundException;
import com.example.twitterclone.exception.UnauthorizedException;
import com.example.twitterclone.exception.UserAlreadyExistsException;
import com.example.twitterclone.mapper.UserMapper;
import com.example.twitterclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //--> consturctor'ı yazıyor. required <--> final
public class UserServiceImpl implements UserService{

    //user request dto -> user -> repository -> user -> user response dto

    // @Autowired yazmadım cünkü RequiredArgsConstructor final alanlar için constructor olusturuyor.
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    //MAPPERRRRRR
    @Override
    public UserResponseDto register(UserRegisterRequestDto registerRequest) {
        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new UserAlreadyExistsException("Username already taken");
        }
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException("Email already in use");
        }
        //repository ile konuscagım için user'a ceviriyoruz.
        User userToSave = userMapper.toEntity(registerRequest);

        userToSave.setPassword(passwordEncoder.encode(registerRequest.password()));

        User savedUser = userRepository.save(userToSave);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto login(UserLoginRequestDto loginRequest) {
        // 1. find the user
        // login with username or email
        User user = userRepository.findByUsernameOrEmail(loginRequest.username(), loginRequest.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
        // 2. confirm password
        // passwordEncoder.matches(normal-password, hashed-password)
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return userMapper.toResponseDto(user);
    }

    @Override
    public void deleteUserAccount(Long id, Long authenticatedUserId) {
        //1. to check if it is authenticated
        if (!id.equals(authenticatedUserId)) {
            throw new UnauthorizedException("You are not authorized to delete this account.");
        }
        //2. to check if user exists
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    //-> atomicity,  rollback, dirty checking
    @Transactional
    public UserResponseDto updateUserProfile(Long id, UserUpdateRequestDto updateRequest, Long authenticatedUserId) {

        //bu methodda olanlar:
        //1 - yapmaya izni var mı kontrol et
        //2 - update edilecek userı bul
        //3 - eğer username blank veya null değilse ve eğer username eskisiyle aynıysa veya baska bir kullanıcı tarafından kullanılıyorsa exception fırlat.
        //4 - değilse yeni username ile update et
        //5- aynısını email için yap
        //6 ve 7 - bio ve profile img için eğer bos değilse yenile.
        //bu userın değişikliklerini kaydet ve dto'ya cevir.

        if (!id.equals(authenticatedUserId)) {
            throw new UnauthorizedException("You are not authorized to update this profile.");
        }

        User userToUpdate = findUserById(id);

        if (updateRequest.username() != null && !updateRequest.username().isBlank()) {
            if (!userToUpdate.getUsername().equals(updateRequest.username()) && userRepository.existsByUsername(updateRequest.username())) {
                throw new UserAlreadyExistsException("Username already taken: " + updateRequest.username());
            }
            userToUpdate.setUsername(updateRequest.username());
        }
        if (updateRequest.email() != null && !updateRequest.email().isBlank()) {
            if (!userToUpdate.getEmail().equals(updateRequest.email()) && userRepository.existsByEmail(updateRequest.email())) {
                throw new UserAlreadyExistsException("Email already in use: " + updateRequest.email());
            }
            userToUpdate.setEmail(updateRequest.email());
        }
        if (updateRequest.bio() != null) {
            userToUpdate.setBio(updateRequest.bio());
        }
        if (updateRequest.profileImageUrl() != null) {
            userToUpdate.setProfileImageUrl(updateRequest.profileImageUrl());
        }

        User updatedUser = userRepository.save(userToUpdate);

        return userMapper.toResponseDto(updatedUser);
    }
    //---------------------FIND METHODS---------------------------------
    @Override
    public UserResponseDto findUserProfileById(Long id) {
        //OUTER WORLD --> FRONTEND - MOBILE
        User user = findUserById(id);
        return userMapper.toResponseDto(user);
    }

    @Override
    public User findUserById(Long id) {
        //INTERNAL SERVICES (bundan dolayı User döndürüyorum)
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findUserByUsername(String username) {
        //INTERNAL SERVICES
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }
}
