package bootwildfly.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class User implements UserDetails{

    @Id
    @NotNull
    @com.fasterxml.jackson.annotation.JsonProperty("username")
    private String username;

    @NotNull
    @com.fasterxml.jackson.annotation.JsonProperty("password")
    private String password;


    public User() {

    }

    public User(UserDetails userDetails){
        this();
        this.setUsername(userDetails.getUsername());
        this.setPassword(userDetails.getPassword());
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getPassword() {
        return password;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setUsername(String username) {
        this.username = username;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }


}
