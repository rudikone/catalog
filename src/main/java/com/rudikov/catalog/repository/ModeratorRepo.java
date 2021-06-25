package com.rudikov.catalog.repository;

import com.rudikov.catalog.model.entity.Moderator;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModeratorRepo extends CrudRepository<Moderator, Long> {

    Optional<Moderator> findByLogin(String login);
}
