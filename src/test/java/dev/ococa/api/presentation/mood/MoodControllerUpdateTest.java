package dev.ococa.api.presentation.mood;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ococa.api.application.service.mood.MoodPutResponseDto;
import dev.ococa.api.application.service.mood.MoodService;
import dev.ococa.api.domain.model.mood.Note;
import dev.ococa.api.infrastructure.query.mood.MoodQueryService;
import dev.ococa.api.presentation.mood.MoodController;
import dev.ococa.api.presentation.mood.MoodPutRequestDto;

@WebMvcTest(MoodController.class)
public class MoodControllerUpdateTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoodQueryService MoodQueryService;

    @MockBean
    private MoodService moodService;

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * ■ 正常系テスト
     * 有効な入力パラメータが送られた場合、サービス層が呼び出され、200が返ること
     * @throws Exception
     */
    @Test
    public void testUpdateMood_ValidInput() throws Exception {
        MoodPutRequestDto req = MoodPutRequestDto.builder()
            .moodId("1234")
            .pictureId("null")
            .tag("t03")
            .note(new Note("unit test"))
            .build();

        MoodPutResponseDto res = new MoodPutResponseDto();

        String jsonRequest = mapper.writeValueAsString(req);
        String jsonResponse = mapper.writeValueAsString(res);

        Mockito
            .when(moodService.rewriteBody(eq("1234"), eq("alice"), any(MoodPutRequestDto.class)))
            .thenReturn(res);

        mockMvc.perform(
            put("/api/mood/1234")
            .with(jwt().jwt(builder -> builder.subject("alice")))
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(content().json(jsonResponse));

        // サービス層への呼び出しが正しく行われていること
        Mockito.verify(moodService).rewriteBody(eq("1234"), eq("alice"), any(MoodPutRequestDto.class));
    }

}
