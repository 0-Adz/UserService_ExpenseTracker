package com.adzdevhub.userservice.repository;

import com.adzdevhub.userservice.entities.UserInfo;
import com.adzdevhub.userservice.entities.UserInfoDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, String> {

    Optional<UserInfo> findByUserId(String userId);
}
