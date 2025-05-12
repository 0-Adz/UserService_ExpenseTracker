package com.adzdevhub.userservice.consumer;

import com.adzdevhub.userservice.entities.UserInfoDto;
import com.adzdevhub.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AuthServiceConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData){
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc. - can use redis distributed lock - hint: use userId
            userService.createOrUpdateUser(eventData);
        } catch (Exception e) {
            System.out.println("AuthServiceConsumer: Exception is thrown while kafka event");
        }
    }
}
