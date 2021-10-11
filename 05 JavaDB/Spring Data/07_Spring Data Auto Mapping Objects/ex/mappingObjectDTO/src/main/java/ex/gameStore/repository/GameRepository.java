package ex.gameStore.repository;

import ex.gameStore.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

    Game findByTitle(String title);
}
