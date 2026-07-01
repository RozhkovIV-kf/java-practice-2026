package Task_2.shop.app;

import Task_2.shop.user.api.UserConsoleOperations;
import Task_2.shop.user.application.UserService;
import Task_2.shop.user.infrastructure.persistence.UserFileRepository;
import Task_2.shop.user.infrastructure.persistence.UserMapper;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("users.txt", new UserMapper());
        UserService userService = new UserService(userFileRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
