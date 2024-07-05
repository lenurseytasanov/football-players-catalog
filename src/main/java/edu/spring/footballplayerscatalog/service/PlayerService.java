package edu.spring.footballplayerscatalog.service;

import edu.spring.footballplayerscatalog.client.WebSocketClient;
import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.exception.PlayerNotFoundException;
import edu.spring.footballplayerscatalog.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final TeamService teamService;

    private final WebSocketClient webSocketClient;

    @Transactional
    public Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id.toString()));
    }

    @Transactional
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player savePlayer(Player player) {
        Team team = teamService.findOrSaveTeam(player.getTeam().getName());
        Player newPlayer = new Player(
                player.getId(),
                player.getFirstname(),
                player.getLastname(),
                player.getSex(),
                player.getBirthdate(),
                team,
                player.getCountry());
        newPlayer = playerRepository.save(newPlayer);
        log.info("Player {} saved", newPlayer);
        webSocketClient.sendToEveryone(playerRepository.findAll());
        return newPlayer;
    }
}
