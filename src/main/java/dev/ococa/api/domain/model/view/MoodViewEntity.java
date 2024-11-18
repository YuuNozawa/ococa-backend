package dev.ococa.api.domain.model.view;

import java.time.LocalDateTime;

import dev.ococa.api.presentation.mood.MoodPostRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class MoodViewEntity {
    private String moodId;
    private String userId;
    private LocalDateTime viewDate;
    private String timeZone;

    public static MoodViewEntity createFromDtoWithIdAndUser(String id, String userId, MoodPostRequestDto dto) {
        MoodViewEntity entity = new MoodViewEntity();
        entity.setMoodId(id);
        entity.setUserId(userId);
        entity.setViewDate(dto.getViewDate());
        entity.setTimeZone(dto.getTimeZone());
        return entity;
    }
}
