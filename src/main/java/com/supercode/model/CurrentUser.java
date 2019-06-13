package com.supercode.model;

import com.supercode.entity.User;
import com.supercode.enums.AccessLevel;
import com.supercode.enums.UserStatus;
import org.slf4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * User object for authentication
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public CurrentUser(User user) {
        super(user.getUserId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getUserId();
    }

    public String getRole() {
        return user.getUserType().toString();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                '}';
    }
}
