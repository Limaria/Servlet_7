package model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class User { //DataSet
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NaturalId
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    public User(){}
    public User(String login,  String email,String pass){
        this.login = login;
        this.password = pass;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}