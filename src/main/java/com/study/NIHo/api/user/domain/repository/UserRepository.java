package com.study.NIHo.api.user.domain.repository;

import com.study.NIHo.api.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);

    Optional<User> findByLoginInfoEmail(final String email);

}
