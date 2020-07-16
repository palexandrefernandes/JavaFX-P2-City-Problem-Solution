package models;

import javax.persistence.*;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name="super", nullable = false)
    private boolean sudo;

    public Admin() {}

    public Admin(String name, String password, String email, boolean sudo) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.sudo = sudo;
    }

    public Admin(int id, String name, String password, String email, boolean sudo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sudo = sudo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSudo() {
        return sudo;
    }

    public void setSudo(boolean sudo) {
        this.sudo = sudo;
    }

    public void setId(int id) {
        this.id = id;
    }
}
