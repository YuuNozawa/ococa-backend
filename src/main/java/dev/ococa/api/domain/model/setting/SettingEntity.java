package dev.ococa.api.domain.model.setting;

public class SettingEntity {
    private String userId;
    private Boolean privateMode;
    private Boolean allowSearch;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Boolean getPrivateMode() {
        return privateMode;
    }
    public void setPrivateMode(Boolean privateMode) {
        this.privateMode = privateMode;
    }
    public Boolean getAllowSearch() {
        return allowSearch;
    }
    public void setAllowSearch(Boolean allowSearch) {
        this.allowSearch = allowSearch;
    }
    
}
