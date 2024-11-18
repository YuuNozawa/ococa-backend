package dev.ococa.api.presentation.user;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.ococa.api.domain.model.user.UserEntity;
import dev.ococa.api.domain.model.user.UserRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    UserRepository userRepository;

    @GetMapping("/api/user/{id}/all")
    public List<UserEntity> getUsers(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return userRepository.getAllUserById(userId);
    }
    @GetMapping("/api/user/{id}/requester")
    public List<UserEntity> getRequestsById(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return userRepository.getRequestsById(userId);
    } 
    @GetMapping("/api/user/{id}/friends")
    public List<UserEntity> getFriendsById(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return userRepository.getFriendsById(userId);
    } 
    @GetMapping("/api/user/search/{id}")
    public List<UserEntity> searchById(@PathVariable String id) {
        return userRepository.searchUsersById(id);
    }
}
