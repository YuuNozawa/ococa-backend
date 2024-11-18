package dev.ococa.api.infrastructure.repository.view;

import org.apache.ibatis.annotations.Mapper;

import dev.ococa.api.domain.model.view.MoodViewEntity;

@Mapper
public interface MoodViewMapper {
    public void insertView(MoodViewEntity mood);
}
