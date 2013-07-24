package com.exadel.borsch.dao;

import com.exadel.borsch.util.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author Andrey Zhilka
 */
public class User {

    public static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private String name;
    private String email;
    private UUID id;
    private List<AccessRight> accessRights = new ArrayList<>();
    private Locale locale = new Locale("en_US");

    public User() {
        id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UUID getId() {
        return id;
    }

    public boolean addAccessRights(List<AccessRight> toAdd) {
        return accessRights.addAll(toAdd);
    }

    public boolean discardAccessRights(List<AccessRight> toDiscard) {
        return accessRights.removeAll(toDiscard);
    }

    public String getHash() {
        String usersHash = Encoder.encodeWithMD5(name, email);
        return usersHash;
    }
}
