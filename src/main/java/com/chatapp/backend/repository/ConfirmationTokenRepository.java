package com.chatapp.backend.repository;

import com.chatapp.backend.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Transactional
    @Modifying
    @Query("update confirmation_token c set c.confirmedAt = ?1 where c.token = ?2")
    void updateConfirmedAtByToken(LocalDateTime confirmedAt, String token);

    Optional<ConfirmationToken> findByToken(String token);
}
