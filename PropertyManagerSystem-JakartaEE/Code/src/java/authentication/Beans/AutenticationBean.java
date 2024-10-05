package Authentication.Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@Named(value = "authBean")
@SessionScoped
public class AutenticationBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    private String username;
    private String password;
    private String passwordv; // password confirmation field
    private String fname;
    private String lname;
    private String email;
    private String verificationcode; // user input verification code
    private String verificationcode1; // generated verification code
    private boolean logged = false;
    private boolean recovery = false;
    private Wuser ruser;

    public AutenticationBean() {
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordv() {
        return passwordv;
    }

    public void setPasswordv(String passwordv) {
        this.passwordv = passwordv;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getVerificationcode1() {
        return verificationcode1;
    }

    public void setVerificationcode1(String verificationcode1) {
        this.verificationcode1 = verificationcode1;
    }

    public boolean isRecovery() {
        return recovery;
    }

    public void setRecovery(boolean recovery) {
        this.recovery = recovery;
    }

    public Wuser getRuser() {
        return ruser;
    }

    public void setRuser(Wuser ruser) {
        this.ruser = ruser;
    }
    

    // Generate a hash of the password
    public String HashConvert(String oripassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(oripassword.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public String validateUser() {
        
        System.out.println("Login hoy kmne????");
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser user = getUser();
        if (user != null) {
            String p1 = user.getPassword();
            String p2 = HashConvert(password);
            if (!p1.equals(p2)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed!", "Incorrect password.");
                context.addMessage(null, message);
                return null;
            }
            logged = true;
            return "default.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed!", "Username does not exist.");
            context.addMessage(null, message);
            return null;
        }
    }

    // Generate verification code and send it to the user's email address
    public String createVerificationCode() {
        recovery = false;
        Wuser usr = getUserbyEmail();
        if (usr == null) {
            return sendEmailVerification();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email '" + email + "' already registered.", "Please choose a different email.");
            context.addMessage(null, message);
            return null;
        }
    }

    // Send email with verification code
    private String sendEmailVerification() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "2525");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("CENTRE@glassfish.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Verification Code");
            verificationcode1 = genVerificationCode();
            message.setText("Your Verification Code: " + verificationcode1);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return null;
        }
        return "register.xhtml";
    }

    // Generate a random string (verification code)
    public String genVerificationCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 20) {
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        return salt.toString();
    }

    // Create a new user and store it in the database
    public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Wuser existingUser = getUser();
        Wuser emailUser = getUserbyEmail();
        if (existingUser == null && emailUser == null) {
            if (!password.equals(passwordv)) {
                context.addMessage(null, new FacesMessage("Passwords do not match!"));
                return null;
            }
            if (!verificationcode.equals(verificationcode1)) {
                context.addMessage(null, new FacesMessage("Invalid verification code!"));
                return null;
            }
            Wuser newUser = new Wuser();
            newUser.setFirstname(fname);
            newUser.setLastname(lname);
            newUser.setPassword(HashConvert(password));
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setSince(new Date());
            try {
                utx.begin();
                em.persist(newUser);
                utx.commit();
                resetFields(); // Reset user data after registration
                return "index.xhtml?faces-redirect=true";
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating user!", "Contact system administrator."));
                return null;
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or Email already exists.", "Choose different credentials."));
            return null;
        }
    }


    private Wuser getUser() {
        try {
            return (Wuser) em.createNamedQuery("Wuser.findByUsername")
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    private Wuser getUserbyEmail() {
        try {
            return (Wuser) em.createNamedQuery("Wuser.findByEmail")
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    public String createRecoveryCode() {
    FacesContext context = FacesContext.getCurrentInstance();
    Wuser user = getUserbyEmail();
    
    if (user != null) {
        this.ruser = user; // Set the user for recovery
        recovery = true; // Set the recovery flag
        return sendRecoveryEmail(); // Send recovery email
    } else {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Email not found", "No account associated with this email.");
        context.addMessage(null, message);
        return null;
    }
}

private String sendRecoveryEmail() {
    Properties props = new Properties();
    props.put("mail.smtp.host", "localhost");
    props.put("mail.smtp.port", "2525");
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);
    
    try {
        message.setFrom(new InternetAddress("CENTRE@glassfish.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Password Recovery");
        verificationcode1 = genVerificationCode(); // Generate recovery code
        message.setText("Your Password Recovery Code: " + verificationcode1);
        Transport.send(message);
    } catch (MessagingException ex) {
        ex.printStackTrace();
        return null;
    }
    return "userRecovery.xhtml"; // Redirect to recovery page
}
public String resetUser() {
    FacesContext context = FacesContext.getCurrentInstance();

    // Check if the new password and confirmation match
    if (!password.equals(passwordv)) {
        FacesMessage message = new FacesMessage("The passwords do not match, please try again!");
        context.addMessage(null, message);
        return null;
    }

    // Check if the verification code matches the one sent to the user
    if (!verificationcode.equals(verificationcode1)) {
        FacesMessage message = new FacesMessage("The recovery code is incorrect, please try again!");
        context.addMessage(null, message);
        return null;
    }

    // Update the user's password in the database
    ruser.setPassword(HashConvert(password));  // Convert the new password to hash

    try {
        // Begin the transaction, update the user, and commit the changes
        utx.begin();
        em.merge(ruser);  // Use merge to update the existing user in the database
        utx.commit();

        // Reset the fields to clear out the data after successful recovery
        resetFields();

        // Redirect the user to the login page after a successful reset
        return "login.xhtml?faces-redirect=true";
    } catch (Exception e) {
        FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Error recovering user!",
                "Unexpected error during password recovery. Please contact the system administrator.");
        context.addMessage(null, message);
        Logger.getAnonymousLogger().log(Level.SEVERE, "Unable to reset user password", e);
        return null;
    }
}

private void resetFields() {
    username = null;
    password = null;
    passwordv = null;
    fname = null;
    lname = null;
    verificationcode = null;
    verificationcode1 = null;
    email = null;
    ruser = null;  // Reset the recovered user object
}

}