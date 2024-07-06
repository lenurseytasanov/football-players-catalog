package edu.spring.footballplayerscatalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.spring.footballplayerscatalog.controller.PlayerController;
import edu.spring.footballplayerscatalog.domain.Country;
import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.domain.Sex;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.dto.PlayerRequestDto;
import edu.spring.footballplayerscatalog.mapper.PlayerMapper;
import edu.spring.footballplayerscatalog.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
@Import(PlayerMapper.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    public void whenExistsById_thenReturnPlayer() throws Exception {
        Player testPlayer = new Player(
                1L,
                "test",
                "test",
                Sex.MALE,
                LocalDate.MIN,
                new Team(1L, "team"),
                Country.RUSSIA);

        when(playerService.getPlayer(eq(testPlayer.getId()))).thenReturn(testPlayer);

        mockMvc.perform(get("/api/players/{id}", testPlayer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testPlayer.getId()))
                .andExpect(jsonPath("$.firstname").value(testPlayer.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(testPlayer.getLastname()))
                .andExpect(jsonPath("$.sex").value(testPlayer.getSex().toString()))
                .andExpect(jsonPath("$.birthdate").value(testPlayer.getBirthdate().toString()))
                .andExpect(jsonPath("$.team").value(testPlayer.getTeam().getName()))
                .andExpect(jsonPath("$.country").value(testPlayer.getCountry().toString()));
    }

    @Test
    public void whenFindPlayers_thenReturnList() throws Exception {
        Player testPlayer1 = new Player(
                1L,
                "test",
                "test",
                Sex.MALE,
                LocalDate.MIN,
                new Team(1L, "team"),
                Country.RUSSIA);
        Player testPlayer2 = new Player(
                2L,
                "test",
                "test",
                Sex.MALE,
                LocalDate.MIN,
                new Team(1L, "team"),
                Country.RUSSIA);

        when(playerService.getAllPlayers()).thenReturn(List.of(testPlayer1, testPlayer2));

        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void whenCreatePlayer_thenReturnPlayerWithId() throws Exception {
        Player testPlayer = new Player(
                1L,
                "test",
                "test",
                Sex.MALE,
                LocalDate.MIN,
                new Team(1L, "team"),
                Country.RUSSIA);
        PlayerRequestDto testPlayerDtp = new PlayerRequestDto(
                testPlayer.getFirstname(),
                testPlayer.getLastname(),
                testPlayer.getSex(),
                testPlayer.getBirthdate(),
                testPlayer.getTeam().getName(),
                testPlayer.getCountry()
        );

        when(playerService.savePlayer(any())).thenReturn(testPlayer);

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPlayerDtp)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testPlayer.getId()))
                .andExpect(jsonPath("$.firstname").value(testPlayer.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(testPlayer.getLastname()))
                .andExpect(jsonPath("$.sex").value(testPlayer.getSex().toString()))
                .andExpect(jsonPath("$.birthdate").value(testPlayer.getBirthdate().toString()))
                .andExpect(jsonPath("$.team").value(testPlayer.getTeam().getName()))
                .andExpect(jsonPath("$.country").value(testPlayer.getCountry().toString()));
    }

    @Test
    public void whenUpdatePlayer_thenReturnPlayerWithId() throws Exception {
        Player testPlayer = new Player(
                1L,
                "test",
                "test",
                Sex.MALE,
                LocalDate.MIN,
                new Team(1L, "team"),
                Country.RUSSIA);
        PlayerRequestDto testPlayerDtp = new PlayerRequestDto(
                testPlayer.getFirstname(),
                testPlayer.getLastname(),
                testPlayer.getSex(),
                testPlayer.getBirthdate(),
                testPlayer.getTeam().getName(),
                testPlayer.getCountry()
        );

        when(playerService.savePlayer(any())).thenReturn(testPlayer);

        mockMvc.perform(put("/api/players/{id}", testPlayer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPlayerDtp)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testPlayer.getId()))
                .andExpect(jsonPath("$.firstname").value(testPlayer.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(testPlayer.getLastname()))
                .andExpect(jsonPath("$.sex").value(testPlayer.getSex().toString()))
                .andExpect(jsonPath("$.birthdate").value(testPlayer.getBirthdate().toString()))
                .andExpect(jsonPath("$.team").value(testPlayer.getTeam().getName()))
                .andExpect(jsonPath("$.country").value(testPlayer.getCountry().toString()));
    }
}
