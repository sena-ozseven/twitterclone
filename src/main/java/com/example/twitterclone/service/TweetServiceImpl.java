package com.example.twitterclone.service;

import com.example.twitterclone.dto.tweetDTO.TweetRequestDto;
import com.example.twitterclone.dto.tweetDTO.TweetResponseDto;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.entity.User;

import com.example.twitterclone.exception.ResourceNotFoundException;
import com.example.twitterclone.exception.UnauthorizedException;
import com.example.twitterclone.mapper.TweetMapper;
import com.example.twitterclone.repository.TweetRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TweetServiceImpl implements TweetService{

    private UserService userService;
    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto, Long authenticatedUserId) {
        //1. find the user that will post it
        User user = userService.findUserById(authenticatedUserId);
        //2. mapper ile dto'yu entity'e çevir
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto, user);
        //3. database'e kaydet
        Tweet savedTweet = tweetRepository.save(tweet);
        //4. kaydedilen tweet'i ResponseDTO'ya çevirip döndür
        return tweetMapper.toResponseDto(savedTweet);
    }

    @Override
    public TweetResponseDto findTweetById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + id));
        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public void deleteTweet(Long id, Long authenticatedUserId) {
        Tweet tweetToDelete = tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + id));

        if (!tweetToDelete.getUser().getId().equals(authenticatedUserId)) {
            throw new UnauthorizedException("You are not authorized to delete this tweet.");
        }
        
        tweetRepository.delete(tweetToDelete);
    }
}
