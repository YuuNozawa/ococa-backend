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

import dev.ococa.api.application.service.mood.MoodBodyQueryResponseDto;
import dev.ococa.api.application.service.mood.MoodService;
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
     * 正しいJWTが付与されている場合、サービス層が呼び出され、200が返ること
     * @throws Exception
     */
    @Test
    public void testGetMoodBodyByUserId_HappyPath() throws Exception {
        List<MoodBodyQueryResponseDto> li = new ArrayList<>();
        li.add(new MoodBodyQueryResponseDto());

        Mockito.when(queryService.getMoodBodyByUserId(eq("alice"))).thenReturn(li);
        
        mockMvc.perform(get("/api/mood").with(jwt().jwt(builder -> builder.subject("alice"))))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0]").exists());

        // サービス層への呼び出しが正しく行われていること
        Mockito.verify(queryService).getMoodBodyByUserId(eq("alice"));
    }

    /**
     * ■ 異常系テスト
     * JWTが付与されていない場合は認証エラーになること
     * @throws Exception
     */
    @Test
    public void testGetMoodBodyByUserId_Unauthorize() throws Exception {
        mockMvc.perform(get("/api/mood"))
        .andExpect(status().isUnauthorized());

        // サービス層のメソッドが呼ばれていないこと
        Mockito
            .verify(queryService, Mockito.never())
            .getMoodBodyByUserId(any(String.class));
    }
}
