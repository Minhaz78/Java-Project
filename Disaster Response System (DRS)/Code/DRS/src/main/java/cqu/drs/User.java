package cqu.drs;

/**
 * Represents a User in the Disaster Response System (DRS).
 * Each user has a username, password, and role (e.g., Admin or User).
 * 
 * This class provides getter and setter methods for handling the user's information.
 * It is used during login, authentication, and for role-based navigation.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class User {
    
    /** The username of the user */
    private String username;
    
    /** The password of the user */
    private String password;
    
    /** The role of the user, either 'Admin' or 'User' */
    private String role;

    /**
     * Constructs a new User with the specified username, password, and role.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the username of this user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this user.
     * 
     * @param username the new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of this user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this user.
     * 
     * @param password the new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of this user.
     * 
     * @return the role of the user (e.g., 'Admin' or 'User')
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of this user.
     * 
     * @param role the new role for the user
     */
    public void setRole(String role) {
        this.role = role;
    }
}
