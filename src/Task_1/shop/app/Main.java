package Task_1.shop.app;

import Task_1.shop.user.api.UserConsoleOperations;
import Task_1.shop.user.infrastructure.persistence.UserDatabaseRepository;
import Task_1.shop.user.infrastructure.persistence.UserFileRepository;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("user.txt");
        UserDatabaseRepository userDatabaseRepository = new UserDatabaseRepository();
        UserConsoleOperations operations = new UserConsoleOperations(userFileRepository);

        while (true) {
            operations.showMenu();
        }
    }
}
