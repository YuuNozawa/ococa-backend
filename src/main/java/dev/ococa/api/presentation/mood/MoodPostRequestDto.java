package dev.ococa.api.presentation.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.mood.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoodPostRequestDto {
    private String emotionId;
    private String pictureId;
    private int moodLevel;
    private String tag;
    private Note note;
    private String alt;
    private LocalDateTime startAt;
    private LocalDateTime likeDate;
    private LocalDateTime viewDate;
    private String timeZone;
}
