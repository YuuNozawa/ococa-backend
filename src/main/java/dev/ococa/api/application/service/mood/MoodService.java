package dev.ococa.api.application.service.mood;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ococa.api.domain.exception.UnauthorizedMoodAccessException;
import dev.ococa.api.domain.model.like.MoodLikeRepository;
import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.domain.model.mood.MoodRepository;
import dev.ococa.api.domain.model.view.MoodViewEntity;
import dev.ococa.api.domain.model.view.MoodViewRepository;
import dev.ococa.api.domain.service.mood.MoodOwnershipService;
import dev.ococa.api.presentation.mood.MoodPostRequestDto;
import dev.ococa.api.presentation.mood.MoodPutRequestDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MoodService {
    MoodOwnershipService moodOwnershipService;
    MoodRepository moodRepository;
    MoodLikeRepository moodLikeRepository;
    MoodViewRepository moodViewRepository;
    
    public List<Mood> getCombinedMoodStatusesByUserId(String userId) {
        // System.out.println("仮の動作確認");
        // List<Mood> moods = moodRepository.findByUserId(userId);
        // moods.forEach(m -> {
        //     System.out.println(m.getMoodId());
        //     System.out.println("いいね：" + m.getLikes().size());
        // });
        return moodRepository.getCombinedMoodStatusesByUserId(userId);
    }

    public MoodPostResponseDto insertMood(String userId, MoodPostRequestDto dto) {
        Mood mood = Mood.createFromDtoWithUser(userId, dto);
        if( !moodOwnershipService.canUserCreateMood(mood, userId) ) {
            throw new UnauthorizedMoodAccessException();
        }
        moodRepository.save(mood);
        return MoodPostResponseDto.of(mood);
    }

    public MoodPutResponseDto rewriteBody(String moodId, String userId, MoodPutRequestDto dto) {
        Mood mood = moodRepository.findById(moodId);
        if( !moodOwnershipService.canUserCreateMood(mood, userId) ) {
            throw new UnauthorizedMoodAccessException();
        }
        mood.rewriteBody(dto);
        moodRepository.save(mood);
        return MoodPutResponseDto.of(dto);
    }

    public MoodPutResponseDto archive(String moodId, String userId, MoodPutRequestDto dto) {
        Mood mood = moodRepository.findById(moodId);
        if( !moodOwnershipService.isUserMoodOwner(moodId, userId) ) {
            throw new UnauthorizedMoodAccessException();
        }
        mood.archive(dto.getEndAt());
        moodRepository.save(mood);
        return MoodPutResponseDto.of(dto);   
    }

    public MoodPostResponseDto like(String moodId, String userId, MoodPostRequestDto dto) {
        Mood mood = moodRepository.findById(moodId);
        mood.like(dto.getLikeDate(), dto.getTimeZone());
        moodRepository.save(mood);
        // moodLikeRepository.insertLike(like);
        return MoodPostResponseDto.of(mood);
    }

    public MoodPostResponseDto cancelLike(String moodId, String userId) {
        moodLikeRepository.cancelLike(moodId, userId);
        return MoodPostResponseDto.of(moodId, userId);
    }

    public MoodPostResponseDto insertView(String id, String userId, MoodPostRequestDto dto) {
        MoodViewEntity view = MoodViewEntity.createFromDtoWithIdAndUser(id, userId, dto);
        moodViewRepository.insertView(view);
        return MoodPostResponseDto.of(view);
    }

    public boolean deleteMood(String moodId, String userId) {
        if( !moodOwnershipService.isUserMoodOwner(moodId, userId) ) {
            throw new UnauthorizedMoodAccessException();
        }
        return moodRepository.deleteOne(moodId) > 0;
    }
}
