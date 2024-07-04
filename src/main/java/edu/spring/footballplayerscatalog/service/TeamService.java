package edu.spring.footballplayerscatalog.service;

import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team addTeam(String teamName) {
        return teamRepository.findByName(teamName)
                .orElseGet(() -> teamRepository.save(new Team(null, teamName)));
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
