package ex.gameStore.service.impl;

import ex.gameStore.model.dto.GameAddDto;
import ex.gameStore.model.dto.GameDeleteDto;
import ex.gameStore.model.dto.GameTitlePriceDescriptionReleaseDateDto;
import ex.gameStore.model.dto.GameTitlePriceDto;
import ex.gameStore.model.entity.Game;
import ex.gameStore.model.entity.User;
import ex.gameStore.repository.GameRepository;
import ex.gameStore.repository.UserRepository;
import ex.gameStore.service.GameService;
import ex.gameStore.service.UserService;
import ex.gameStore.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, ValidationUtil validationUtil, ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (userIsNotAdmin()) return;

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violation(gameAddDto);
        if (!violations.isEmpty()) {
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        Game game = modelMapper.map(gameAddDto, Game.class);

        this.gameRepository.save(game);
        System.out.println("Added " + gameAddDto.getTitle());
    }

    private boolean userIsNotAdmin() {
        if (this.userService.getLoginUser() == null || !this.userService.isLoggedInUserAdmin()) {
            System.out.println("Only Admin can add/edit/delete games");
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void editGame(String[] values) {

        if (userIsNotAdmin()) return;

        Long gameId = Long.parseLong(values[1]);

        Game game = this.gameRepository.findById(gameId).orElse(null);

        if (game == null) {
            System.out.println("Id is not correct");
            return;
        }

        for (int i = 2; i < values.length; i++) {
            String[] tokens = values[i].split("=");
            String value = tokens[0].toLowerCase();
            switch (value) {
                case "price" -> game.setPrice(new BigDecimal(tokens[1]));
                case "size" -> game.setSize(Double.parseDouble(tokens[1]));
                case "title" -> game.setTitle(tokens[1]);
                case "trailer" -> game.setTrailer(tokens[1]);
                case "imageThumbnail" -> game.setImageThumbnail(tokens[1]);
                case "description" -> game.setDescription(tokens[1]);
                case "releasedate" -> game.setReleaseDate(LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            }
        }
        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public void getAllGames() {
      this.gameRepository.findAll()
                .stream()
                .map(game -> modelMapper.map(game, GameTitlePriceDto.class))
                .collect(Collectors.toList())
        .forEach(System.out::println);
    }

    @Override
    public void detailGame(String gameName) {
        Game game = this.gameRepository.findByTitle(gameName);
        if(game==null){
            System.out.println("There is no game with provided name");
            return;
        }

        GameTitlePriceDescriptionReleaseDateDto detailGameDto = modelMapper.map(game, GameTitlePriceDescriptionReleaseDateDto.class);

        System.out.println(detailGameDto);
    }


    @Override
    public void deleteGame(GameDeleteDto gameDeleteDto) {
        if (userIsNotAdmin()) return;

        Long id =gameDeleteDto.getId();


        Game game = this.gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("Id is not correct");
            return;
        }
        //премахваме връзките на юзера и купената от него игра, която предстои да изтрием от базата, иначе не може да се изтрие играта
        List<User> allByGameId = this.userService.getAllByGameId(id);
        for (User user : allByGameId) {
            List<Game> games = user.getGames();
           games.removeIf(g->g.getId()==id);
           this.userRepository.save(user);
        }

        this.gameRepository.deleteById(gameDeleteDto.getId());
        System.out.println("Deleted " + game.getTitle());
    }
}
