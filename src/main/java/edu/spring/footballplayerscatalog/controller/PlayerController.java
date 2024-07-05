package edu.spring.footballplayerscatalog.controller;

import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.dto.PlayerRequestDto;
import edu.spring.footballplayerscatalog.dto.PlayerResponseDto;
import edu.spring.footballplayerscatalog.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    private final SimpMessagingTemplate messagingTemplate;

    private PlayerResponseDto toDto(Player player) {
        return new PlayerResponseDto(
                player.getId(),
                player.getFirstname(),
                player.getLastname(),
                player.getSex(),
                player.getBirthdate(),
                player.getTeam().getName(),
                player.getCountry()
        );
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok(toDto(playerService.getPlayer(playerId).orElseThrow()));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers().stream().map(this::toDto).toList());
    }

    private void sendPlayerList() {
        messagingTemplate.convertAndSend("/topic",
                playerService.getAllPlayers().stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> addPlayer(@Valid @RequestBody PlayerRequestDto addPlayerRequestDto) {
        Player player = new Player(
                null,
                addPlayerRequestDto.firstname(),
                addPlayerRequestDto.lastname(),
                addPlayerRequestDto.sex(),
                addPlayerRequestDto.birthdate(),
                new Team(null, addPlayerRequestDto.team()),
                addPlayerRequestDto.country());
        Player createdPlayer = playerService.savePlayer(player);
        sendPlayerList();
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/players/" + createdPlayer.getId().toString()))
                .body(toDto(createdPlayer));
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> editPlayer(@Valid @RequestBody PlayerRequestDto editPlayerRequestDto,
                                                        @PathVariable Long playerId) {
        Player player = new Player(
                playerId,
                editPlayerRequestDto.firstname(),
                editPlayerRequestDto.lastname(),
                editPlayerRequestDto.sex(),
                editPlayerRequestDto.birthdate(),
                new Team(null, editPlayerRequestDto.team()),
                editPlayerRequestDto.country());
        Player updatedPlayer = playerService.savePlayer(player);
        sendPlayerList();
        return ResponseEntity
                .ok()
                .body(toDto(updatedPlayer));
    }
}
