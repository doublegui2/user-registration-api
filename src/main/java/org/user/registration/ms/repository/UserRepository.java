package org.user.registration.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.user.registration.ms.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
