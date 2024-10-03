package cqu.mavenproject1;

/**
 * The User class represents a user of the application. It holds information
 * about the user's username, email, password, gender, birthday, and role.
 *
 * @author asif
 */
public class User {

    private String username;  // The username of the user
    private String email;     // The email of the user
    private String password;  // The password of the user
    private String gender;    // The gender of the user
    private String birthday;  // The birthday of the user
    private String role;      // The role of the user (e.g., Visitor, Resource Manager, Emergency Responder)

    /**
     * Constructor to create a User object.
     *
     * @param username the username of the user
     * @param email the email address of the user
     * @param password the password of the user
     * @param gender the gender of the user
     * @param birthday the birthday of the user
     * @param role the role of the user
     */
    public User(String username, String email, String password, String gender, String birthday, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the birthday of the user.
     *
     * @return the birthday of the user
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }
}
