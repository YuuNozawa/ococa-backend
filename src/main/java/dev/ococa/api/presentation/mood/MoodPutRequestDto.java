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
public class MoodPutRequestDto {
    @NotNull(message = "idは必須")
    private String moodId;
    private String pictureId;
    private String tag;
    private Note note;
    private LocalDateTime endAt;
}
