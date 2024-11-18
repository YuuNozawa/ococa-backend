package dev.ococa.api.infrastructure.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.FriendEntity;

@Mapper
public interface FriendMapper {
    List<FriendEntity> getFriendsById(String id);
}
