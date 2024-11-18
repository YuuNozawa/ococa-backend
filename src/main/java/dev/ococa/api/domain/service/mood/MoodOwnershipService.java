package dev.ococa.api.domain.service.mood;

import org.springframework.stereotype.Service;

import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.domain.model.mood.MoodRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MoodOwnershipService {
    private MoodRepository moodRepository;

    public boolean canUserCreateMood(Mood mood, String userId) {
        return mood.getUserId().equals(userId);
    }

    public boolean isUserMoodOwner(String moodId, String userId) {
        Mood existedEntity = moodRepository.findById(moodId);
        if(existedEntity != null) {
            return existedEntity.getUserId().equals(userId);
        }
        return false;
    }
}
