package Task_2.shop.user.api;

import Task_2.shop.user.domain.User;
import Task_2.shop.user.application.UserService;

import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                findById();
            }
            break;
            case "4": {
                updateProfile();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void findById() {
        System.out.println("Сейчас будем искать пользователя по id");
        System.out.print("Введите id пользователя: ");

        String id = scanner.nextLine();
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            System.out.println("Пользователь найден");
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void updateProfile() {
        System.out.println("Обновление данных пользователя");
        System.out.print("Введите email пользователя для поиска: ");

        String email = scanner.nextLine();
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isPresent()) {
            System.out.println("Пользователь найден");
            System.out.println("Текущее описание профиля" + userOptional.get().getProfileDescription());
            System.out.print("Введие новое описание профиля: ");
            String newDescription = scanner.nextLine();

            boolean updated = userService.updateProfile(email, newDescription);
            if (updated) {
                System.out.println("Обновлено успешно");
            }
            else {
                System.out.println("Ошибка при обновлении");
            }
        }
        else {
            System.out.println("Пользователь с таким email не найден/не существует");
        }
    }
}
