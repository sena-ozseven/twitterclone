package com.example.twitterclone.repository;

import com.example.twitterclone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TweetRepository extends JpaRepository<Tweet, Long> {

    //QUERY DERIVATION --> spring daya jpa sadece metot isminyle sorgu yapabiliyor. parser ile analiz ediyor ve anahtar kelimeler/desenler arıyor

    //TweetServiceImpl'da kullanabilmek için
    List<Tweet> findByUserIdOrderByCreatedAtDesc(Long userId);

}
