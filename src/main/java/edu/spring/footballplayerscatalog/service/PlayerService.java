package edu.spring.footballplayerscatalog.service;

import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final TeamService teamService;

    @Transactional
    public Optional<Player> getPlayer(Long id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player savePlayer(Player player) {
        Team team = teamService.addTeam(player.getTeam().getName());
        Player newPlayer = new Player(
                player.getId(),
                player.getFirstname(),
                player.getLastname(),
                player.getSex(),
                player.getBirthdate(),
                team,
                player.getCountry());
        return playerRepository.save(newPlayer);
    }
}
