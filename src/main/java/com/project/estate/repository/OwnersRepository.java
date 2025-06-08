package com.project.estate.repository;

import com.project.estate.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnersRepository extends JpaRepository<Owner, Long> {
    // Removed findByUserId(Long userId) as owners are now common to all users
}
