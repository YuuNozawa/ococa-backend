package dev.ococa.api.presentation.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.mood.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoodPutRequestDto {
    private String moodId;
    private String pictureId;
    private String tag;
    private Note note;
    private LocalDateTime endAt;
}
