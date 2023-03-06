package com.example.musicloud.business;
import static org.junit.Assert.*;

import com.example.musicloud.objects.Playlist;

import org.junit.Test;
public class AccessPlaylistTest {
    @Test
    public void testAccessPlaylist (){
        AccessPlaylist accessPlaylist;
        Playlist playlist;
        System.out.println ("\n Starting testAccessPlaylist");

        accessPlaylist = new AccessPlaylist();
        playlist = new Playlist("good playlist", "this is a good playlist");
        assertNotNull(accessPlaylist);
        assertEquals(playlist, accessPlaylist.insertPlaylist(playlist));
    }
}
