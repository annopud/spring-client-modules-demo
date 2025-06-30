package dev.annopud.spring_clients.album;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlbumClientTest {

    @Autowired
    private AlbumClient albumClient;

    @Test
    void findAll() {
        var albums = albumClient.findAll();
        assertEquals(100, albums.size());
    }

}