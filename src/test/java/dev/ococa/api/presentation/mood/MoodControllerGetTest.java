package dev.ococa.api.presentation.mood;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.ococa.api.application.service.mood.LikedUserQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodBodyQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodService;
import dev.ococa.api.application.service.mood.ViewedUserQueryResponseDto;
import dev.ococa.api.domain.model.mood.Mood;
import dev.ococa.api.infrastructure.query.mood.MoodQueryService;

@WebMvcTest(MoodController.class)
public class MoodControllerGetTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoodQueryService queryService;

    @MockBean
    private MoodService moodService;

    /**
     * ■ 正常系テスト
     * /api/mood
     * 正しいJWTが付与されている場合、サービス層が呼び出され、200が返ること
     * @throws Exception
     */
    @Test
    public void testGetMoodBodyByUserId_HappyPath() throws Exception {
        List<MoodBodyQueryResponseDto> li = new ArrayList<>();
        li.add(new MoodBodyQueryResponseDto());

        Mockito
            .when(queryService.getMoodBodyByUserId(eq("alice")))
            .thenReturn(li);
        
        mockMvc
            .perform(
                get("/api/mood")
                .with(jwt().jwt(builder -> builder.subject("alice")))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").exists());

        // サービス層への呼び出しが正しく行われていること
        Mockito.verify(queryService).getMoodBodyByUserId(eq("alice"));
    }

    /**
     * ■ 異常系テスト
     * /api/mood
     * JWTが付与されていない場合は認証エラーになること
     * @throws Exception
     */
    @Test
    public void testGetMoodBodyByUserId_Unauthorize() throws Exception {
        mockMvc
            .perform(get("/api/mood"))
            .andExpect(status().isUnauthorized());

        // サービス層のメソッドが呼ばれていないこと
        Mockito
            .verify(queryService, Mockito.never())
            .getMoodBodyByUserId(any(String.class));
    }

    /**
     * ■ 正常系テスト
     * /api/mood/{id}/favorite
     * @throws Exception
     */
    @Test
    public void testGetLikedUser_HappyPath() throws Exception {
        List<LikedUserQueryResponseDto> li = new ArrayList<>();
        li.add(new LikedUserQueryResponseDto());

        Mockito
            .when(queryService.getLikedUserByMoodId("1234"))
            .thenReturn(li);
        
        mockMvc
            .perform(
                get("/api/mood/1234/favorite")
                .with(jwt().jwt(builder -> builder.subject("alice")))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").exists());
        
        Mockito.verify(queryService).getLikedUserByMoodId(eq("1234"));

    }

    /**
     * ■ 異常系テスト
     * /api/mood/{id}/favorite
     * @throws Exception
     */
    @Test
    public void testGetLikedUser_Unauthorized() throws Exception {
        mockMvc
            .perform(get("/api/mood/1234/favorite"))
            .andExpect(status().isUnauthorized());

        // サービス層のメソッドが呼ばれていないこと
        Mockito
            .verify(queryService, Mockito.never())
            .getLikedUserByMoodId(any(String.class));
    }

    /**
     * ■ 正常系テスト
     * /api/mood/{id}/view
     * @throws Exception
     */
    @Test
    public void testGetViewedUser_HappyPath() throws Exception {
        List<ViewedUserQueryResponseDto> li = new ArrayList<>();
        li.add(new ViewedUserQueryResponseDto());

        Mockito
            .when(queryService.getViewedUserByMoodId("1234"))
            .thenReturn(li);

        mockMvc
            .perform(
                get("/api/mood/1234/view")
                .with(jwt().jwt(builder -> builder.subject("alice")))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").exists());

        Mockito.verify(queryService).getViewedUserByMoodId("1234");
    }

    /**
     * ■ 異常系テスト
     * /api/mood/{id}/view
     * @throws Exception
     */
    @Test
    public void testGetViewedUser_Unauthorized() throws Exception {
        mockMvc
            .perform(get("/api/mood/1234/view"))
            .andExpect(status().isUnauthorized());

        Mockito
            .verify(queryService, Mockito.never())
            .getViewedUserByMoodId(any(String.class));
    }

    /**
     * ■ 正常系テスト
     * /api/mood/status/all
     * @throws Exception
     */
    @Test
    public void testGetCombinedMoodStatusesByUserId_HappyPath() throws Exception {
        List<Mood> li = new ArrayList<>();
        li.add(new Mood());

        Mockito
            .when(moodService.getCombinedMoodStatusesByUserId("alice"))
            .thenReturn(li);

        mockMvc
            .perform(
                get("/api/mood/status/all")
                .with(jwt().jwt(builder -> builder.subject("alice")))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").exists());

        Mockito.verify(moodService).getCombinedMoodStatusesByUserId("alice");

    }

    /**
     * ■ 異常系テスト
     * /api/mood/status/all
     * @throws Exception
     */
    @Test
    public void testGetCombinedMoodStatusesByUserId_Unauthorized() throws Exception {
        mockMvc
            .perform(get("/api/mood/status/all"))
            .andExpect(status().isUnauthorized());

        Mockito
            .verify(moodService, Mockito.never())
            .getCombinedMoodStatusesByUserId(any(String.class));
    }
}
