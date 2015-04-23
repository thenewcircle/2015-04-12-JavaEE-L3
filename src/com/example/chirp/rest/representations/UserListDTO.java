package com.example.chirp.rest.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.example.chirp.model.User;

@XmlRootElement(name="user-list")
@JsonWriteNullProperties(false)
public final class UserListDTO implements Serializable {

	private static final long serialVersionUID = 8613456080588682667L;
	private List<UserDTO> entity;

	public UserListDTO() {

	}
	
	public UserListDTO(Collection<User> users, boolean microVersion) {
		entity = new ArrayList<UserDTO>(users.size());
		for (User u : users) {
			UserDTO dto = new UserDTO(u, microVersion);
			entity.add(dto);
		}
	}
	
	@XmlElement(name="user")
	public List<UserDTO> getEntity() {
		return entity;
	}

	public void setEntity(List<UserDTO> entity) {
		this.entity = entity;
	}

	public List<User> toEntity() {
		ArrayList<User> results = new ArrayList<User>(entity.size());
		for (UserDTO dto : entity) {
			User user = dto.toEntity();
			results.add(user);
		}
		return results;
	}

	@XmlAttribute
	public int getSize() {
		return entity.size();
	}
	
	public void setSize(int size) { }
	
}

