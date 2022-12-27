package com.chatapp.backend.repository;

import com.chatapp.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Modifying
    @Query("update AppUser a set a.enabled = true where a.email = ?1")
    void enableAppUser(String email);

    Optional<AppUser> findByEmail(String email);
}
