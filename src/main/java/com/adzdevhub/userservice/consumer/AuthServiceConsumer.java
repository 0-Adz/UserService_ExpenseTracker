package com.adzdevhub.userservice.consumer;

import com.adzdevhub.userservice.entities.UserInfoDto;
import com.adzdevhub.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData){
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc. - can use redis distributed lock - hint: use userId
            userService.createOrUpdateUser(eventData);
        } catch (Exception e) {
            System.out.println("AuthServiceConsumer: Exception is thrown while kafka event");
        }
    }
}
