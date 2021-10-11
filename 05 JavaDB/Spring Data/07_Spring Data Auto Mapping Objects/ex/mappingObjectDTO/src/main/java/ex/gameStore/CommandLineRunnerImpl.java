package ex.gameStore;

import ex.gameStore.model.dto.GameAddDto;
import ex.gameStore.model.dto.GameDeleteDto;
import ex.gameStore.model.dto.UserLoginDto;
import ex.gameStore.model.dto.UserRegisterDto;
import ex.gameStore.service.GameService;
import ex.gameStore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader reader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter command:");
            String[] input = reader.readLine().split("\\|");

            switch (input[0].toLowerCase()) {
                case "registeruser" -> this.userService.registerUser(new UserRegisterDto(
                        input[1],
                        input[2],
                        input[3],
                        input[4]
                ));
                case "loginuser" -> this.userService.loginUser(new UserLoginDto(input[1], input[2]));
                case "logout" -> this.userService.logout();
                case "addgame" -> this.gameService.addGame(new GameAddDto(
                        input[1],
                        new BigDecimal(input[2]),
                        Double.parseDouble(input[3]),
                        input[4],
                        input[5],
                        input[6],
                        input[7]
                ));
                case "editgame" -> this.gameService.editGame(input);
                case "deletegame" -> this.gameService.deleteGame(new GameDeleteDto(Long.parseLong(input[1])));
                case "allgames"-> gameService.getAllGames();
                case "detailgame" -> gameService.detailGame(input[1]);
                //purchase Game first before test OwnedGames functionality
                case "purchasegame" -> userService.purchaseGame(input[1]);
                case "ownedgames" -> userService.printGamesForCurrentLoggedUser();
                default -> System.out.println("Please enter correct command");
            }
        }
    }

}
