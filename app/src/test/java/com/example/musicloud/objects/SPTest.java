package com.example.musicloud.objects;
import static org.junit.Assert.*;

import org.junit.Test;

public class SPTest {
    @Test
    public void testSP1() {
        SP sp;

        System.out.println("\nStarting testSP");

        final Song s = (new Song("Not Enough To Give", "Ketsa", "Ketsa - Not Enough To Give"));
        final Playlist p = (new Playlist("name", "description"));
        sp = new SP (s,p);
        assertNotNull(sp);
        assertEquals("Not Enough To Give",sp.getSongName());
        assertEquals("name",sp.getPlaylistName());
        System.out.println("finished testSP");
    }
}
