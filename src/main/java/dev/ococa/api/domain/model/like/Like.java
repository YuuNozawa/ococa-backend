package dev.ococa.api.domain.model.like;

import java.time.LocalDateTime;

import dev.ococa.api.presentation.mood.MoodPostRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Like {
    private String moodId;
    private String userId;
    private LocalDateTime likeDate;
    private String timeZone;

    public static Like createFromDtoWithIdAndUser(String moodId, String userId, MoodPostRequestDto dto) {
        Like entity = new Like();
        entity.setMoodId(moodId);
        entity.setUserId(userId);
        entity.setLikeDate(dto.getLikeDate());
        entity.setTimeZone(dto.getTimeZone());
        return entity;
    }
}
