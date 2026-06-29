package Task_1.shop.user.infrastructure.persistence;

import Task_1.shop.user.domain.User;
import Task_1.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String user;
            while ((user = reader.readLine()) != null) {
                String[] userParts = user.split("\\|");
                if (userParts[0].equals(id)) {
                    return new User(userParts[0], userParts[1], userParts[2], userParts[3]);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }
}
