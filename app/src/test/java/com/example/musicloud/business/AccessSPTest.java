package com.example.musicloud.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.musicloud.objects.Playlist;
import com.example.musicloud.objects.SP;
import com.example.musicloud.objects.Song;
import com.example.musicloud.persistence.stubs.SPPersistenceStub;

import org.junit.Before;
import org.junit.Test;

public class AccessSPTest {
    private AccessSP accessSP;

    @Before
    public void setUp() {
        this.accessSP = new AccessSP(new SPPersistenceStub());
    }

    @Test
    public void test1()
    {
        System.out.println("\nStarting test AccessSP");

        final SP sp = accessSP.getPS("playlist1");
        assertNotNull(sp);
        System.out.println (sp);

        System.out.println("Finished test AccessSP");
    }
}
