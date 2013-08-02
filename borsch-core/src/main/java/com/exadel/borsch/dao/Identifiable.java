package com.exadel.borsch.dao;

import java.util.UUID;

/**
 * @author Andrew Zhilka
 */
public class Identifiable {
    private UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }
}
