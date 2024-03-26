package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.User;

public class UserRepositoryStub implements UserRepository {
    @Override
    public void save(User user) {

    }

    @Override
    public User findUserByUserId(Long userId) {
        return new User(userId, null, null);
    }
}
