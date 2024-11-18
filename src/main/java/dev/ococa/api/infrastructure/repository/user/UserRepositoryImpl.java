package dev.ococa.api.infrastructure.repository.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ococa.api.domain.model.user.UserEntity;
import dev.ococa.api.domain.model.user.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    UserMapper userMapper;

    public UserEntity getUserById(String id) {
        return userMapper.getUserById(id);
    }
    
    public List<UserEntity> getAllUserById(String id) {
        return userMapper.getAllUserById(id);
    }

    public List<UserEntity> getFriendsById(String id) {
        return userMapper.getFriendsById(id);
    }

    public List<UserEntity> getRequestsById(String id) {
        return userMapper.getRequestsById(id);
    }

    public List<UserEntity> searchUsersById(String id) {
        return userMapper.searchUsersById(id);
    }
}
