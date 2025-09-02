package com.example.twitterclone.mapper;


import com.example.twitterclone.dto.tweetDTO.TweetRequestDto;
import com.example.twitterclone.dto.tweetDTO.TweetResponseDto;
import com.example.twitterclone.entity.Tweet;
import com.example.twitterclone.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    //toEntity -- yeni bir tweet olustruruken
    public Tweet toEntity(TweetRequestDto tweetRequestDto, User user) {
        if (tweetRequestDto == null || user == null) {
            return null;
        }
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDto.content());
        tweet.setUser(user);
        return tweet;
    }

    //tweeti dış dünyaya açarken
    public TweetResponseDto toResponseDto(Tweet tweet) {
        if (tweet == null) {
            return null;
        }
        return new TweetResponseDto(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getUser().getId(),
                tweet.getUser().getUsername(),
                tweet.getLikes().size()
        );
    }
}
