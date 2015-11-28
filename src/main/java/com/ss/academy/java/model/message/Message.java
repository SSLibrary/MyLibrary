package com.ss.academy.java.model.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.ss.academy.java.model.user.User;


@Entity
@Table(name = "MESSAGES")
public class Message {

	@Id
	@Column(name = "MESSAGE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer message_id;

	@Size(min = 1, max = 60)
	@Column(name = "header", nullable = false)
	private String header;

	@Column(name = "body", nullable = false)
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
    private Date date = new Date();

	@Column(name = "is_new", nullable = false)
	private int isNew = 1;
	
	@Column(name = "in_reply_to", nullable = false)
	private Integer in_reply_to = 0;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;
	
	
	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public Integer getIn_reply_to() {
		return in_reply_to;
	}

	public void setIn_reply_to(Integer in_reply_to) {
		this.in_reply_to = in_reply_to;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Messages [message_id=" + message_id + ", header=" + header + ", body=" + body 
			 + ", date=" + date + "]";
	}
}