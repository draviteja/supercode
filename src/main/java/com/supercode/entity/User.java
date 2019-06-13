package com.supercode.entity;

import com.supercode.enums.AccessLevel;
import com.supercode.enums.UserStatus;
import com.supercode.enums.UserType;
import com.supercode.listener.AuditEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity for table user
 */
@Entity
@EntityListeners(AuditEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="USERS")
public class User extends Auditable<String>{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="USER_ID", nullable = false)
    String userId;

    @Column(name="FIRST_NAME", nullable = false)
    String firstName;

    @Column(name="LAST_NAME")
    String lastName;

    @Column(name="EMAIL_ID")
    String emailId;

    @Column(name="STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(name="ACCESS_LEVEL", nullable = false)
    @Enumerated(EnumType.STRING)
    AccessLevel accessLevel;

    @Column(name="USER_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    UserType userType;

    @Column(name="COMMENTS")
    String comments;

    @Column(name="PASSWORD", nullable = false)
    String password;

    @Column(name="PASSWORD_UPDATED_AT", nullable = false)
    Timestamp passwordUpdatedAt;

    @Column(name="LAST_LOGGED_IN", nullable = false)
    Timestamp lastLoggedIn;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", status='" + status + '\'' +
                ", accessLevel=" + accessLevel +
                ", userType=" + userType +
                ", comments='" + comments + '\'' +
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
        return Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(emailId, user.emailId) &&
                status == user.status &&
                accessLevel == user.accessLevel &&
                userType == user.userType &&
                Objects.equals(comments, user.comments) &&
                Objects.equals(password, user.password) &&
                Objects.equals(passwordUpdatedAt, user.passwordUpdatedAt) &&
                Objects.equals(lastLoggedIn, user.lastLoggedIn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, firstName, lastName, emailId, status, accessLevel, userType, comments, password, passwordUpdatedAt, lastLoggedIn);
    }
}

