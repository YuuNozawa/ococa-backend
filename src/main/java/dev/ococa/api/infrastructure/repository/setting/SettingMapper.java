package dev.ococa.api.infrastructure.repository.setting;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.setting.SettingEntity;

@Mapper
public interface SettingMapper {
    SettingEntity getSettingByUserId(String id);
}
