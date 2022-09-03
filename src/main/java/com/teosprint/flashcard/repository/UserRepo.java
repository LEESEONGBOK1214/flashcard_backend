package com.teosprint.flashcard.repository;

import com.teosprint.flashcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
