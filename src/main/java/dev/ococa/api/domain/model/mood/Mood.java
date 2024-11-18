package dev.ococa.api.domain.model.mood;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dev.ococa.api.presentation.mood.MoodPostRequestDto;
import dev.ococa.api.presentation.mood.MoodPutRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Mood {
    private String moodId;
    private String userId;
    private String emotionId;
    private String pictureId;
    private int moodLevel;
    private String tag;
    private Note note;
    private String alt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime updatedAt;
    
    @Setter(AccessLevel.PUBLIC)
    private List<Like> likes;

    public void like(LocalDateTime likeDate, String timeZone) {
        this.getLikes().add(new Like(this.getMoodId(), this.getUserId(), likeDate, timeZone));
    }

    public void rewriteBody(MoodPutRequestDto dto) {
        this.setPictureId(dto.getPictureId());
        this.setTag(dto.getTag());
        this.setNote(dto.getNote());
    }

    public void archive(LocalDateTime endAt) {
        this.setEndAt(endAt);
    }

    public static Mood createFromDtoWithUser(String userId, MoodPostRequestDto dto) {
        Mood entity = new Mood();
        
        entity.setMoodId(generateId());
        entity.setUserId(userId);

        entity.setEmotionId(dto.getEmotionId());
        entity.setPictureId(dto.getPictureId());
        entity.setMoodLevel(dto.getMoodLevel());
        entity.setTag(dto.getTag());
        entity.setNote(dto.getNote());
        entity.setAlt(dto.getAlt());
        entity.setStartAt(dto.getStartAt());
        return entity;
    }

    // あとでMoodIdという値オブジェクトを作ってそっちのConstructorに移動する
    private static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
