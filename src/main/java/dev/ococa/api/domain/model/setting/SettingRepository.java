package dev.ococa.api.domain.model.setting;

public interface SettingRepository {
    public SettingEntity getSettingByUserId(String id);
}
