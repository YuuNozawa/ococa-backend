package dev.ococa.api.infrastructure.repository.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ococa.api.domain.model.like.Like;
import dev.ococa.api.domain.model.like.MoodLikeRepository;

@Repository
public class MoodLikeRepositoryImpl implements MoodLikeRepository {
    @Autowired
    MoodLikeMapper moodLikeMapper;

    public void insertLike(Like mood) {
        moodLikeMapper.insertLike(mood);
    }

    public void cancelLike(String moodId, String userId) {
        moodLikeMapper.cancelLike(moodId, userId);
    }
}
