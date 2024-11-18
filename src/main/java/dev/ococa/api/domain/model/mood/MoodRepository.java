package dev.ococa.api.domain.model.mood;

import java.util.List;

public interface MoodRepository {
    public Mood findById(String id);
    public List<Mood> findByUserId(String userId);
    public List<Mood> getCombinedMoodsByUserId(String userId);
    public List<Mood> getCombinedMoodStatusesByUserId(String userId);
    public void save(Mood mood);
    public int deleteOne(String id);
}
