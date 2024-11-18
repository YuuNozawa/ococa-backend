package dev.ococa.api.domain.model;

import java.time.LocalDateTime;

public class RequestEntity {
    private String requesterId;
    private String requesteeId;
    private LocalDateTime requestDate;
    private int status;
    
    public String getRequesterId() {
        return requesterId;
    }
    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }
    public String getRequesteeId() {
        return requesteeId;
    }
    public void setRequesteeId(String requesteeId) {
        this.requesteeId = requesteeId;
    }
    public LocalDateTime getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
