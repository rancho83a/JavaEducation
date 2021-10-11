package ex.gameStore.service;

import ex.gameStore.model.dto.GameAddDto;
import ex.gameStore.model.dto.GameDeleteDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);


    void deleteGame(GameDeleteDto parseLong);

    void editGame(String[] input);

    void getAllGames();

    void detailGame(String gameName);
}
