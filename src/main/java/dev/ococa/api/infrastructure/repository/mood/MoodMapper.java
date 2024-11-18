package dev.ococa.api.infrastructure.repository.mood;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.mood.Mood;

@Mapper
public interface MoodMapper {
    Mood findById(String id);
    List<Mood> findByUserId(String userId);
    List<Mood> getCombinedMoodsByUserId(String userId);
    // このメソッドgetCombinedMoodsByUserIdとほぼ同じでいらない
    List<Mood> getCombinedMoodStatusesByUserId(String userId);
    void save(Mood mood);
    int deleteOne(String id);
}
