package dev.ococa.api.infrastructure.query.mood;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.application.service.mood.LikedUserQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodBodyQueryResponseDto;
import dev.ococa.api.application.service.mood.ViewedUserQueryResponseDto;

@Mapper
public interface MoodQueryMapper {
    List<MoodBodyQueryResponseDto> getMoodBodyByUserId(String userId);
    List<LikedUserQueryResponseDto> getLikedUserByMoodId(String moodId);
    List<ViewedUserQueryResponseDto> getViewedUserByMoodId(String moodId);
}