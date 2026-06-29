package Task_1.shop.user.repository;

import Task_1.shop.user.domain.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}
