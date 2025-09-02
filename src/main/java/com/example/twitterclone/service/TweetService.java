package com.example.twitterclone.service;

import com.example.twitterclone.dto.tweetDTO.TweetRequestDto;
import com.example.twitterclone.dto.tweetDTO.TweetResponseDto;

import java.util.List;

public interface TweetService {

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto, Long authenticatedUserId);
    TweetResponseDto findTweetById(Long id);
    void deleteTweet(Long id, Long authenticatedUserId);


    //in my Twitter business logic, you cannot update/edit a tweet, thus I'm not including the update method.
}
