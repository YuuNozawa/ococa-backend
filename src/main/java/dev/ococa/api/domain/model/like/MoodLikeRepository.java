package dev.ococa.api.domain.model.like;

public interface MoodLikeRepository {
    public void insertLike(Like moodLike);
    public void cancelLike(String moodId, String userId);
}
