package dev.ococa.api.application.service.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.mood.Note;
import dev.ococa.api.presentation.mood.MoodPutRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoodPutResponseDto {
    static MoodPutResponseDto of(MoodPutRequestDto body) {
        return new MoodPutResponseDto(
             body.getMoodId()
            ,body.getPictureId()
            ,body.getTag()
            ,body.getNote()
            ,body.getEndAt()
        );
    }
    private String moodId;
    private String pictureId;
    private String tag;
    private Note note;
    private LocalDateTime endAt;
}