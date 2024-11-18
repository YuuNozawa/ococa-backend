package dev.ococa.api.infrastructure.repository.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.ococa.api.domain.model.view.MoodViewEntity;
import dev.ococa.api.domain.model.view.MoodViewRepository;

@Repository
public class MoodViewRepositoryImpl implements MoodViewRepository {
    @Autowired
    MoodViewMapper moodViewMapper;

    public void insertView(MoodViewEntity moodView) {
        moodViewMapper.insertView(moodView);
    }
}
