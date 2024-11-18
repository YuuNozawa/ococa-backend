package dev.ococa.api.infrastructure.repository.mood;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ococa.api.domain.model.mood.Like;
import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.domain.model.mood.MoodRepository;

/**
 * ここでインピーダンスミスマッチを解消する.
 */
@Repository
public class MoodRepositoryImpl implements MoodRepository {
    @Autowired
    MoodMapper moodMapper;

    @Autowired
    LikeMapper likeMapper;
    public Mood findById(String id) {
        Mood mood = moodMapper.findById(id);
        List<Like> likes = likeMapper.findById(id);
        mood.setLikes(likes);
        return mood;
    }    
    public List<Mood> findByUserId(String userId) {
        List<Mood> mood = moodMapper.findByUserId(userId);
        List<String> ids = mood.stream().map(m -> m.getMoodId()).distinct().collect(Collectors.toList());
        List<Like> bulkLikes = likeMapper.findByIds(ids);
        mood.forEach(m -> {
            List<Like> likes = bulkLikes.stream().filter( like -> like.getMoodId().equals(m.getMoodId()) ).collect(Collectors.toList());
            m.setLikes(likes);
        });
        return mood;
    }
    public List<Mood> getCombinedMoodsByUserId(String userId) {
        return moodMapper.getCombinedMoodsByUserId(userId);
    }
    public List<Mood> getCombinedMoodStatusesByUserId(String userId) {
        return moodMapper.getCombinedMoodStatusesByUserId(userId);
    }
    public void save(Mood mood) {
        moodMapper.save(mood);
        List<Like> likes = likeMapper.findById(mood.getMoodId())
            .stream()
            .filter(like -> !mood.getLikes().contains(like))
            .collect(Collectors.toList());
        likes.forEach(l -> {
            likeMapper.save(l);
        });
    }
    public int deleteOne(String id) {
        return moodMapper.deleteOne(id);
    }
}
