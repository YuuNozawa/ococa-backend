package dev.ococa.api.application.service.mood;

import java.time.LocalDateTime;

import dev.ococa.api.domain.model.mood.Note;

public class MoodBodyQueryResponseDto {
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
    private long likeCount;
    private long viewCount;
    private boolean likedByCurrentUser;

    public String getMoodId() {
        return moodId;
    }
    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEmotionId() {
        return emotionId;
    }
    public void setEmotionId(String emotionId) {
        this.emotionId = emotionId;
    }
    public String getPictureId() {
        return pictureId;
    }
    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
    public int getMoodLevel() {
        return moodLevel;
    }
    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public Note getNote() {
        return note;
    }
    public void setNote(Note note) {
        this.note = note;
    }
    public String getAlt() {
        return alt;
    }
    public void setAlt(String alt) {
        this.alt = alt;
    }
    public LocalDateTime getStartAt() {
        return startAt;
    }
    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }
    public LocalDateTime getEndAt() {
        return endAt;
    }
    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public long getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }
    public long getViewCount() {
        return viewCount;
    }
    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }
    public boolean getLikedByCurrentUser() {
        return likedByCurrentUser;
    }
    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }
}
