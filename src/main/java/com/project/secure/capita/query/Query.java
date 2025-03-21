package com.project.secure.capita.query;

public class Query {

    //USER
    public static final String COUNT_USE_EMAIL_QUERY = "INSERT INTO users (first_name, last_name, email,password) VALUES(:firstName, :lastName, :email, :password);";
    public static final String INSERT_USER_QUERY = "SELECT COUNT(*) users WHERE email = :email;";
    public static final String INSERT_VERIFICATION_QUERY = "INSERT INTO accountverifications (user_id,url) VALUES (:userId, :url);";

    //ROLE
    public static final String INSER_ROLE_BY_NAME_QUERY = "INSERT INTO userroles (user_id, role_id) VALUES (:userId, :roleId);";
    public static final String INSERT_ROLE_TO_USER = "SELECT * FROM  roles WHERE name = :name;";
}
