package com.andreymasiero.users.repositories;

import java.util.List;

import com.andreymasiero.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findBySocialId(String socialId);
    List<User> queryByNameLike(String name);
}
