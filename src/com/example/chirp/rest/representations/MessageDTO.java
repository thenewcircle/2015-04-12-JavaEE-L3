package com.example.chirp.rest.representations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.example.chirp.model.Message;
import com.example.chirp.model.User;

@XmlRootElement(name = "Message")
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY)
public class MessageDTO {

	private Message entity;

	public MessageDTO() {
		this(null);
	}

	public MessageDTO(Message entity) {
		if (entity != null) {
			this.entity = entity;
		} else {
			this.entity = new Message();
		}
	}

	@XmlAttribute
	public Long getId() {
		return entity.getId();
	}

	public void setId(Long id) {
		entity.setId(id);
	}

	public UserDTO getUser() {
		User user = entity.getUser();
		UserDTO dto = new UserDTO(user, true);
		return dto;
	}

	public void setUser(UserDTO userDTO) {
		User user = userDTO.toEntity();
		entity.setUser(user);
	}

	public String getText() {
		return entity.getText();
	}

	public void setText(String text) {
		entity.setText(text);
	}

	@XmlAttribute
	public String getSelf() {
		Long id = getId();
		if (id == null)
			return null;
		String self = "/chirp-solution/api/message/" + id;
		return self;
	}

	public void setSelf(String self) {
	}

	public Message toEntity() {
		return this.entity;
	}

}
