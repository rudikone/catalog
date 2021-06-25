package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Moderator;
import com.rudikov.catalog.repository.ModeratorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService, UserDetailsService {

    private ModeratorRepo moderatorRepo;

    @Autowired
    public ModeratorServiceImpl(ModeratorRepo moderatorRepo) {
        this.moderatorRepo = moderatorRepo;
    }

    @Override
    public List<Moderator> getAllModerators() {
        List<Moderator> moderators = new ArrayList<>();
        moderatorRepo.findAll().forEach(moderators::add);
        return moderators;
    }

    @Override
    public Moderator getModeratorById(Long id) {
        return moderatorRepo.findById(id).get();
    }

    @Override
    public void save(Moderator moderator) {
        moderatorRepo.save(moderator);
    }

    @Override
    public void delete(Long id) {
        moderatorRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return moderatorRepo.findByLogin(login).get();
    }
}
