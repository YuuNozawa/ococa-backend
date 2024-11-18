package dev.ococa.api.infrastructure.repository.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.like.Like;

@Mapper
public interface MoodLikeMapper {
    public List<Like> getLikesByMoodId(String moodId);
    public void insertLike(Like mood);
    public void cancelLike(String moodId, String userId);
}
