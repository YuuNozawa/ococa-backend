package dev.ococa.api.application.service.mood;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ViewedUserQueryResponseDto {
    private String userId;
    private String userName;
    private String icon;
    private String color;
    private String bio;
    private LocalDateTime likeDate;
    private String timeZone;
}
