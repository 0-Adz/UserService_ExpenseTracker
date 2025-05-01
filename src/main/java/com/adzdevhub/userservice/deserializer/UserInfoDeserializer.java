package com.adzdevhub.userservice.deserializer;

import com.adzdevhub.userservice.entities.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class UserInfoDeserializer implements Deserializer<UserInfoDto> {

    @Override
    public UserInfoDto deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user = null;
        try{
            user = objectMapper.readValue(bytes, UserInfoDto.class);
        } catch (Exception e) {
            System.out.println("Cannot deserialize");
        }
        return user;
    }
}
