package edu.spring.footballplayerscatalog;

import edu.spring.footballplayerscatalog.controller.TeamController;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @MockBean
    private TeamService teamService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindTeams_thenReturnList() throws Exception {
        List<Team> teamList = List.of(new Team(1L, "team1"), new Team(2L, "team2"));

        when(teamService.getAllTeams()).thenReturn(teamList);

        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0]").value("team1"))
                .andExpect(jsonPath("$[1]").value("team2"));
    }
}
