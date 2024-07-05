package edu.spring.footballplayerscatalog.mapper;

import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.domain.Team;
import edu.spring.footballplayerscatalog.dto.PlayerRequestDto;
import edu.spring.footballplayerscatalog.dto.PlayerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponseDto toResponseDto(Player player) {
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

    public Player toEntity(PlayerRequestDto playerRequestDto) {
        return new Player(
                null,
                playerRequestDto.firstname(),
                playerRequestDto.lastname(),
                playerRequestDto.sex(),
                playerRequestDto.birthdate(),
                new Team(null, playerRequestDto.team()),
                playerRequestDto.country());
    }

    public Player toEntity(PlayerRequestDto playerRequestDto, Long playerId) {
        return new Player(
                playerId,
                playerRequestDto.firstname(),
                playerRequestDto.lastname(),
                playerRequestDto.sex(),
                playerRequestDto.birthdate(),
                new Team(null, playerRequestDto.team()),
                playerRequestDto.country());
    }
}
