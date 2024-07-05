package edu.spring.footballplayerscatalog.controller;

import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.dto.PlayerRequestDto;
import edu.spring.footballplayerscatalog.dto.PlayerResponseDto;
import edu.spring.footballplayerscatalog.mapper.PlayerMapper;
import edu.spring.footballplayerscatalog.service.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    private final PlayerMapper playerMapper;

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@NotNull @PathVariable Long playerId) {
        return ResponseEntity.ok(playerMapper.toResponseDto(playerService.getPlayer(playerId)));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers().stream().map(playerMapper::toResponseDto).toList());
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> addPlayer(@Valid @RequestBody PlayerRequestDto addPlayerRequestDto) {
        Player player = playerMapper.toEntity(addPlayerRequestDto);
        Player createdPlayer = playerService.savePlayer(player);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/players/" + createdPlayer.getId().toString()))
                .body(playerMapper.toResponseDto(createdPlayer));
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> editPlayer(@Valid @RequestBody PlayerRequestDto editPlayerRequestDto,
                                                        @NotNull @PathVariable Long playerId) {
        Player player = playerMapper.toEntity(editPlayerRequestDto, playerId);
        Player updatedPlayer = playerService.savePlayer(player);
        return ResponseEntity
                .ok()
                .body(playerMapper.toResponseDto(updatedPlayer));
    }
}
