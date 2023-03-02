package com.example.musicloud.persistence;

import com.example.musicloud.objects.SP;

import java.util.List;

public interface SPPersistence {
    List<SP> getSP (final String songName);

    List<SP> getPS (final String playlistName);
}
