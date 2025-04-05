package ru.skypro.homework.model;

import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_seq")
    @SequenceGenerator(name = "user_data_seq", allocationSize = 1)
    private int id;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 2, max = 16)
    private String firstName;

    @Size(min = 2, max = 16)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid phone pattern")
    private String phone;

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = "Invalid email pattern")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255)")
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<Ad> ads = new HashSet<>();

    private String image;

    public User() {
    }

    public User(int id, String password, String firstName, String lastName, String phone, String email, Role role, String image) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.image = "no_image";
    }

    public int getId() {
        return id;
    }


    public @Size(min = 8, max = 16) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, max = 16) String password) {
        this.password = password;
    }

    public @Size(min = 2, max = 16) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, max = 16) String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, max = 16) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 16) String lastName) {
        this.lastName = lastName;
    }

    public @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid phone pattern") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid phone pattern") String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = "Invalid email pattern") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = "Invalid email pattern") String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id == user.id && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, firstName, lastName, phone, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}