package models;

import java.sql.Date;
import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String password;
    private Date birthdate;
    private String photo;
    private String mail;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return user.mail.equals(this.mail);
    }

    public User(String username, String password, String mail, Date birthdate) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.birthdate = birthdate;
        this.photo = "";
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthdate(Date birthdate) {this.birthdate = birthdate;}

    public void setPhoto(String photo) {this.photo = photo;}

    public Long getId() {return id;}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthdate() {return birthdate;}

    public String getMail() {return mail;}

    public String getPhoto() {return photo;}
}
