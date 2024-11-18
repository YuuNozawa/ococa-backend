package dev.ococa.api.infrastructure.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.user.UserEntity;

@Mapper
public interface UserMapper {
    UserEntity getUserById(String id);
    List<UserEntity> getAllUserById(String id);
    List<UserEntity> getFriendsById(String id);
    List<UserEntity> getRequestsById(String id);
    List<UserEntity> searchUsersById(String id);
}
