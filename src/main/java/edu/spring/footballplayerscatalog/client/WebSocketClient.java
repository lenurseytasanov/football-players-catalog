package edu.spring.footballplayerscatalog.client;

import edu.spring.footballplayerscatalog.domain.Player;
import edu.spring.footballplayerscatalog.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebSocketClient {

    private final SimpMessagingTemplate messagingTemplate;

    private final PlayerMapper playerMapper;

    public void sendToEveryone(List<Player> playerList) {
        messagingTemplate.convertAndSend("/topic",
                playerList.stream().map(playerMapper::toResponseDto).toList());
    }
}
