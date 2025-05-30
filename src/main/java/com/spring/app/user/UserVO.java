package com.spring.app.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO implements UserDetails {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String birth;
	private String fileName;
	private String oriName;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private String sns;
	private Date createdAt;
	private String position;
	private String parent;
	private Long departmentId;
	
	private List<RoleVO> list;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> ar = new ArrayList<>();
		
		for (RoleVO roleVO : this.list) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getRoleName());
			ar.add(g);
		}
		return ar;
	}
}
