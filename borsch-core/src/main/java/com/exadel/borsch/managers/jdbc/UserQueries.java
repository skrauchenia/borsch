package com.exadel.borsch.managers.jdbc;

/**
 * @author Vlad
 */
final class UserQueries {
    public static final String INSERT_USER_QUERY = "INSERT INTO User "
            + "(login,name,email,needEmailNotification,accessRights,locale) "
            + "VALUES (?,?,?,?,?,?);";

    private UserQueries() {

    }
}
