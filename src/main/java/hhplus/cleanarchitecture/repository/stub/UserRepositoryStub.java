package hhplus.cleanarchitecture.repository.stub;

import hhplus.cleanarchitecture.domain.User;
import hhplus.cleanarchitecture.repository.UserRepository;

public class UserRepositoryStub implements UserRepository {
    @Override
    public void save(User user) {

    }

    @Override
    public User findUserByUserId(Long userId) {
        return new User(userId, null, null);
    }
}
