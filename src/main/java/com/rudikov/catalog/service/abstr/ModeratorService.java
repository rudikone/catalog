package com.rudikov.catalog.service.abstr;

import com.rudikov.catalog.model.entity.Moderator;

import java.util.List;

public interface ModeratorService {
    public List<Moderator> getAllModerators();

    public Moderator getModeratorById(Long id);

    public Moderator save(Moderator moderator);

    public void delete(Long id);
    }
