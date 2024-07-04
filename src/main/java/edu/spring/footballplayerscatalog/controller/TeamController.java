package edu.spring.footballplayerscatalog.controller;

import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<String>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams().stream().map(Team::getName).toList());
    }
}
