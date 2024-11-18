package dev.ococa.api.domain.model.user;

import java.util.List;

public interface UserRepository {
    public UserEntity getUserById(String id);
    public List<UserEntity> getAllUserById(String id);
    public List<UserEntity> getFriendsById(String id);
    public List<UserEntity> getRequestsById(String id);
    public List<UserEntity> searchUsersById(String id);
}
