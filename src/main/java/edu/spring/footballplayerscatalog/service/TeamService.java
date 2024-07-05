package edu.spring.footballplayerscatalog.service;

import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team findOrSaveTeam(String teamName) {
        return teamRepository.findByName(teamName)
                .orElseGet(() -> {
                    Team team = teamRepository.save(new Team(null, teamName));
                    log.info("Team {} saved", team);
                    return team;
                });
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
