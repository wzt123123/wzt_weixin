package org.fkjava.weixin.domain.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.fkjava.weixin.domain.InMessage;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "xml") // 指定XML的根元素名称
@XmlAccessorType(XmlAccessType.FIELD) // 指定属性的访问方式为字段
public class EventInMessage extends InMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "Event")
	@JsonProperty("Event")
	private String event;

	@XmlElement(name = "EventKey")
	@JsonProperty("EventKey")
	private String eventKey;

	public EventInMessage() {
		super("event");
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
