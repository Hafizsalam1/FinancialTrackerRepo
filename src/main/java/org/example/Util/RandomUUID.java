package org.example.Util;


import java.util.UUID;

public class RandomUUID {
    public String Random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
