package dev.ococa.api.infrastructure.repository.mood;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.ococa.api.domain.model.mood.Like;

@Mapper
public interface LikeMapper {
    public List<Like> findById(String moodId);
    public List<Like> findByIds(@Param("ids") List<String> moodId);
    public void save(Like like);
}
