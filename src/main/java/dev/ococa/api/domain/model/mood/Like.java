package dev.ococa.api.domain.model.mood;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Like {
    private String moodId;
    private String userId;
    private LocalDateTime likeDate;
    private String timeZone;
}
