package com.example.chirp.rest.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.chirp.model.Message;

@XmlRootElement(name="message-list")
public final class MessageListDTO implements Serializable {

	private static final long serialVersionUID = 8613456080588682667L;
	private List<MessageDTO> entity;

	public MessageListDTO() {
	}
	
	public MessageListDTO(Collection<Message> messages) {
		entity = new ArrayList<MessageDTO>(messages.size());
		for (Message u : messages) {
			MessageDTO dto = new MessageDTO(u);
			entity.add(dto);
		}
	}
	
	@XmlElement(name="message")
	public List<MessageDTO> getEntity() {
		return entity;
	}

	public void setEntity(List<MessageDTO> entity) {
		this.entity = entity;
	}

	public List<Message> toEntity() {
		ArrayList<Message> results = new ArrayList<Message>(entity.size());
		for (MessageDTO dto : entity) {
			Message message = dto.toEntity();
			results.add(message);
		}
		return results;
	}

	@XmlAttribute
	public int getSize() {
		return entity.size();
	}
	
	public void setSize(int size) { }
	
}

