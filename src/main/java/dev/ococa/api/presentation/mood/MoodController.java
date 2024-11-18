package dev.ococa.api.presentation.mood;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.ococa.api.application.service.mood.LikedUserQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodBodyQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodPostResponseDto;
import dev.ococa.api.application.service.mood.MoodPutResponseDto;
import dev.ococa.api.application.service.mood.MoodService;
import dev.ococa.api.application.service.mood.ViewedUserQueryResponseDto;
import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.infrastructure.query.mood.MoodQueryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MoodController {
    MoodService moodService;
    MoodQueryService queryService;

    @GetMapping("/api/mood")
    public ResponseEntity<List<MoodBodyQueryResponseDto>> getMoodBodyByUserId(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        List<MoodBodyQueryResponseDto> body = queryService.getMoodBodyByUserId(userId);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/api/mood")
    public ResponseEntity<MoodPostResponseDto> createMood(@AuthenticationPrincipal Jwt jwt, @RequestBody MoodPostRequestDto dto) {
        String userId = jwt.getSubject();
        MoodPostResponseDto response = moodService.insertMood(userId, dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/mood/{id}")
    public ResponseEntity<MoodPutResponseDto> updateMood(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, @RequestBody MoodPutRequestDto dto) {
        String userId = jwt.getSubject();
        MoodPutResponseDto response = moodService.rewriteBody(id, userId, dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/mood/{id}/archive")
    public ResponseEntity<MoodPutResponseDto> archiveMood(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, @RequestBody MoodPutRequestDto dto) {
        String userId = jwt.getSubject();
        MoodPutResponseDto response = moodService.archive(id, userId, dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/mood/{id}/favorite")
    public ResponseEntity<List<LikedUserQueryResponseDto>> getLikedUser(@PathVariable String id) {
        List<LikedUserQueryResponseDto> body = queryService.getLikedUserByMoodId(id);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/api/mood/{id}/favorite")
    public ResponseEntity<MoodPostResponseDto> likeMood(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, @RequestBody MoodPostRequestDto dto) {
        String userId = jwt.getSubject();
        MoodPostResponseDto response = moodService.like(id, userId, dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/api/mood/{id}/favorite")
    public ResponseEntity<MoodPostResponseDto> cancelLike(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        MoodPostResponseDto response = moodService.cancelLike(id, userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/mood/{id}/view")
    public ResponseEntity<List<ViewedUserQueryResponseDto>> getViewedUser(@PathVariable String id) {
        List<ViewedUserQueryResponseDto> body = queryService.getViewedUserByMoodId(id);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/api/mood/{id}/view")
    public ResponseEntity<MoodPostResponseDto> viewMood(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, @RequestBody MoodPostRequestDto dto) {
        String userId = jwt.getSubject();
        MoodPostResponseDto response = moodService.insertView(id, userId, dto);
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/api/mood/status/all")
    public List<Mood> getCombinedMoodStatusesByUserId(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return moodService.getCombinedMoodStatusesByUserId(userId);
    }

    @DeleteMapping("/api/mood/{id}")
    public ResponseEntity<?> deleteMoodById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        boolean isRemoved = moodService.deleteMood(id, userId);
        if(!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
        
}
