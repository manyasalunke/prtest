package net.javaguides.springboot.security;

import net.javaguides.springboot.model.Admin;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserAdapter implements UserDetails {

    private final Object user; // Replace 'Object' with the actual type of your entity (Admin or Employee)

    public UserAdapter(Object user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (user instanceof Admin) {
            // Assuming the Admin entity has a roles collection
            Admin admin = (Admin) user;
            for (Roles role : admin.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        } else if (user instanceof Employee) {
            // Assuming the Employee entity has a roles collection
            Employee employee = (Employee) user;
            for (Roles role : employee.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if (user instanceof Admin) {
            return ((Admin) user).getPassword();
        } else if (user instanceof Employee) {
            return ((Employee) user).getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (user instanceof Admin) {
            return ((Admin) user).getUserName();
        } else if (user instanceof Employee) {
            return ((Employee) user).getUserName();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming the account doesn't expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming the account isn't locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming the credentials don't expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming the account is always enabled
    }
}

