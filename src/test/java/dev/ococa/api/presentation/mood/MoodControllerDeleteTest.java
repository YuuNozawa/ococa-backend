package dev.ococa.api.presentation.mood;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.ococa.api.application.service.mood.MoodPostResponseDto;
import dev.ococa.api.application.service.mood.MoodService;
import dev.ococa.api.infrastructure.query.mood.MoodQueryService;

@WebMvcTest(MoodController.class)
public class MoodControllerDeleteTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoodQueryService queryService;

    @MockBean
    private MoodService moodService;

    /**
     * ■ 正常系テスト
     * /api/mood/{id}
     * 正しいJWTが付与されている場合、サービス層が呼び出され、200が返ること
     * @throws Exception
     */
    @Test
    public void testDeleteMoodById_HappyPath() throws Exception {
        Mockito
            .when(moodService.deleteMood("1234", "alice"))
            .thenReturn(true);
        
        mockMvc.perform(
            delete("/api/mood/1234")
            .with(jwt().jwt(builder -> builder.subject("alice")))
        )
        .andExpect(status().isOk());

        Mockito.verify(moodService).deleteMood(eq("1234"), eq("alice"));
    }

    /**
     * ■ 異常系テスト
     * /api/mood/{id}
     * 有効なIDとJWTが送信された場合、サービス層がdeleteMoodを実行しfalseを返すケースで、
     * HTTPステータス404が返ること
     * @throws Exception
     */
    @Test
    public void testDeleteMoodById_NotFound() throws Exception {
        Mockito
            .when(moodService.deleteMood("1234", "alice"))
            .thenReturn(false);
        
        mockMvc.perform(
            delete("/api/mood/1234")
            .with(jwt().jwt(builder -> builder.subject("alice")))
        )
        .andExpect(status().isNotFound());

        Mockito.verify(moodService).deleteMood(eq("1234"), eq("alice"));
    }

    /**
     * ■ 異常系テスト
     * /api/mood/{id}
     * JWTが付与されていない場合は認証エラーになること
     * @throws Exception
     */
    @Test
    public void testDeleteMoodById_Forbidden() throws Exception {
        mockMvc
            .perform(delete("/api/mood/{id}", "1234"))
            .andExpect(status().isForbidden());

        Mockito
            .verify(moodService, Mockito.never())
            .deleteMood(any(String.class), any(String.class));
    }

    /**
     * ■ 正常系テスト
     * /api/mood/{id}/favorite
     * 正しいJWTが付与されている場合、サービス層が呼び出され、200が返ること
     */
    @Test
    public void testCancelLike_HappyPath() throws Exception {
        Mockito
            .when(moodService.cancelLike("1234", "alice"))
            .thenReturn(new MoodPostResponseDto());
        
        mockMvc.perform(
            delete("/api/mood/{id}/favorite", "1234")
            .with(jwt().jwt(builder -> builder.subject("alice")))
        )
        .andExpect(status().isOk());

        Mockito.verify(moodService).cancelLike(eq("1234"), eq("alice"));
    }

    /**
     * ■ 異常系テスト
     * /api/mood/{id}/favorite
     * JWTが付与されていない場合は認証エラーになること
     * @throws Exception
     */
    @Test
    public void testCancelLike_Forbidden() throws Exception {
        mockMvc
            .perform(delete("/api/mood/{id}/favorite", "1234"))
            .andExpect(status().isForbidden());

        Mockito
            .verify(moodService, Mockito.never())
            .deleteMood(any(String.class), any(String.class));
    }
}
