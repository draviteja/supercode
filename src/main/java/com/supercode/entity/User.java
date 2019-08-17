package com.supercode.entity;

import com.supercode.enums.AccessLevel;
import com.supercode.enums.UserStatus;
import com.supercode.enums.UserType;
import com.supercode.listener.AuditEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity for table user
 */
@Entity
@EntityListeners(AuditEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table( name="users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {
                    "username"
            }),
            @UniqueConstraint(columnNames = {
                    "email"
            })
        }
)
public class User extends Auditable<String>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @NotEmpty
    @Column(name="EMAIL", nullable = false)
    @Email(message="{errors.invalid_email}")
    private String email;

    @NotEmpty
    @Size(max = 15)
    @Column(name="USERNAME", nullable = false)
    private String username;

    @NotEmpty
    @Column(name="PASSWORD", nullable = false)
    @Size(min=4)
    private String password;

    @Column(name="STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name="PASSWORD_UPDATED_AT")
    private Timestamp passwordUpdatedAt;

    @Column(name="LAST_LOGGED_IN")
    private Timestamp lastLoggedIn;

   /* @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "id"))
    private Set<Role> roles;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /*@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getName(){
        return firstName!=null?firstName:""+" "+lastName!=null?lastName:"";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Timestamp getPasswordUpdatedAt() {
        return passwordUpdatedAt;
    }

    public void setPasswordUpdatedAt(Timestamp passwordUpdatedAt) {
        this.passwordUpdatedAt = passwordUpdatedAt;
    }

    public Timestamp getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + email + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", passwordUpdatedAt=" + passwordUpdatedAt +
                ", lastLoggedIn=" + lastLoggedIn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                status == user.status &&

                Objects.equals(password, user.password) &&
                Objects.equals(passwordUpdatedAt, user.passwordUpdatedAt) &&
                Objects.equals(lastLoggedIn, user.lastLoggedIn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, status, password, passwordUpdatedAt, lastLoggedIn);
    }
}

