package dev.ococa.api.infrastructure.repository.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ococa.api.domain.model.setting.SettingEntity;
import dev.ococa.api.domain.model.setting.SettingRepository;

@Repository
public class SettingRepositoryImpl implements SettingRepository {
    @Autowired
    SettingMapper settingMapper;

    public SettingEntity getSettingByUserId(String id) {
        return settingMapper.getSettingByUserId(id);
    }
}
