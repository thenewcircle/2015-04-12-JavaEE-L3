package com.example.chirp.rest.representations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.example.chirp.model.User;

@XmlRootElement(name="user")
@JsonAutoDetect(fieldVisibility=Visibility.NONE, getterVisibility=Visibility.PUBLIC_ONLY)
public class UserDTO {

	private User entity;
	private boolean microVersion;

	public UserDTO() {
		this(null, false);
	}
	
	public UserDTO(User entity, boolean microVersion) {
		this.microVersion = microVersion;
		if (entity != null) {
			this.entity = entity;
		} else {
			this.entity = new User();
		}
	}

	@XmlAttribute
	public String getUserName() {
		return entity.getUserName();
	}

	public void setUserName(String userName) {
		entity.setUserName(userName);
	}

	public String getRealName() {
		if (microVersion)
			return null;
		return entity.getRealName();
	}

	public void setRealName(String realName) {
		entity.setRealName(realName);
	}

	@XmlAttribute
	public String getSelf() {
		String userName = getUserName();
		if (userName == null)
			return null;
		String self = "/chirp-solution/api/user/" + userName;
		return self;
	}
	
	public void setSelf(String self) {
	}

	public User toEntity() {
		return this.entity;
	}

}
