package dev.ococa.api.infrastructure.query.mood;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ococa.api.application.service.mood.LikedUserQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodBodyQueryResponseDto;
import dev.ococa.api.application.service.mood.ViewedUserQueryResponseDto;

@Service
public class MoodQueryService {
    @Autowired
    MoodQueryMapper mapper;
    
    public List<MoodBodyQueryResponseDto> getMoodBodyByUserId(String userId) {
        return mapper.getMoodBodyByUserId(userId);
    }
    public List<LikedUserQueryResponseDto> getLikedUserByMoodId(String moodId) {
        return mapper.getLikedUserByMoodId(moodId);
    }
    public List<ViewedUserQueryResponseDto> getViewedUserByMoodId(String moodId) {
        return mapper.getViewedUserByMoodId(moodId);
    }
}
