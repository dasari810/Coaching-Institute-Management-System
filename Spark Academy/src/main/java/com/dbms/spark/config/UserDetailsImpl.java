package com.dbms.spark.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dbms.spark.models.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantList = new ArrayList<>();
		if (!user.getIsActive()) {
			return grantList;
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole());
		grantList.add(authority);
		return grantList;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return this.user.getIsEmailVerified();
	}

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}