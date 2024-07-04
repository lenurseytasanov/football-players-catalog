package edu.spring.footballplayerscatalog.repository;

import edu.spring.footballplayerscatalog.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
