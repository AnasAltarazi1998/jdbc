package jdbc;

import java.util.Objects;

public class Employe {
    int id;
    String name;
    String email;
    String password;

    public Employe() {
    }

    public Employe(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employe id(int id) {
        setId(id);
        return this;
    }

    public Employe name(String name) {
        setName(name);
        return this;
    }

    public Employe email(String email) {
        setEmail(email);
        return this;
    }

    public Employe password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employe)) {
            return false;
        }
        Employe employe = (Employe) o;
        return id == employe.id && Objects.equals(name, employe.name) && Objects.equals(email, employe.email) && Objects.equals(password, employe.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
