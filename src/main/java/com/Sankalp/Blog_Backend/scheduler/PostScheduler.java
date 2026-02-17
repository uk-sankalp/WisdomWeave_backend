package com.Sankalp.Blog_Backend.scheduler;

import com.Sankalp.Blog_Backend.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;


@RequiredArgsConstructor
public class PostScheduler {
    private final PostRepo postRepo;

    @Scheduled(fixedRate = 60000)
    public void publishDrafts(){
        System.out.println("Scheduler Running....");
    }
}
