package dev.ococa.api.presentation.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.mood.Note;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoodPostRequestDto {
    @NotNull(message = "idは必須")
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
