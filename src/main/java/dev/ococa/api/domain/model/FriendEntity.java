package dev.ococa.api.domain.model;

import java.time.LocalDateTime;

public class FriendEntity {
    private String userId1;
    private String userId2;
    private LocalDateTime friendshipDate;

    public String getUserId1() {
        return userId1;
    }
    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }
    public String getUserId2() {
        return userId2;
    }
    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }
    public LocalDateTime getFriendshipDate() {
        return friendshipDate;
    }
    public void setFriendshipDate(LocalDateTime friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

    
}
