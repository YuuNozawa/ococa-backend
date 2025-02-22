package dev.ococa.api.application.service.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.like.Like;
import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.domain.model.mood.Note;
import dev.ococa.api.domain.model.view.MoodViewEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class MoodPostResponseDto {
    private String moodId;
    private String userId;
    private String emotionId;
    private String pictureId;
    private int moodLevel;
    private String tag;
    private Note note;
    private String alt;
    private LocalDateTime startAt;
    private LocalDateTime likeDate;
    private String timeZone;
    
    static MoodPostResponseDto of(Mood entity) {
        MoodPostResponseDto result = new MoodPostResponseDto();
        result.setMoodId(entity.getMoodId());
        result.setUserId(entity.getUserId());
        result.setEmotionId(entity.getEmotionId());
        result.setPictureId(entity.getPictureId());
        result.setMoodLevel(entity.getMoodLevel());
        result.setTag(entity.getTag());
        result.setNote(entity.getNote());
        result.setAlt(entity.getAlt());
        result.setStartAt(entity.getStartAt());
        return result;
    }
    static MoodPostResponseDto of(Like entity) {
        MoodPostResponseDto result = new MoodPostResponseDto();
        result.setMoodId(entity.getMoodId());
        result.setUserId(entity.getUserId());
        result.setLikeDate(entity.getLikeDate());
        result.setTimeZone(entity.getTimeZone());
        return result;
    }
    static MoodPostResponseDto of(MoodViewEntity entity) {
        MoodPostResponseDto result = new MoodPostResponseDto();
        result.setMoodId(entity.getMoodId());
        result.setUserId(entity.getUserId());
        result.setLikeDate(entity.getViewDate());
        result.setTimeZone(entity.getTimeZone());
        return result;
    }
    static MoodPostResponseDto of(String moodId, String userId) {
        MoodPostResponseDto result = new MoodPostResponseDto();
        result.setMoodId(moodId);
        result.setUserId(userId);
        return result;
    }
}