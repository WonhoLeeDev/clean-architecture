package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.User;

public interface UserRepository {

    public void save(User user);

    public User findUserByUserId(Long userId);
}
