package com.study.NIHo.repository;

import com.study.NIHo.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);

    Optional<User> findByLoginInfoEmail(final String email);

}
