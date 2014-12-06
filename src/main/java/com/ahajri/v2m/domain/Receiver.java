package com.ahajri.v2m.domain;

// Generated 16 nov. 2014 02:11:02 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.ahajri.v2m.domain.json.serialization.ReceiverDeserializer;
import com.ahajri.v2m.domain.json.serialization.ReceiverSerializer;

/**
 * Receiver generated by hbm2java
 */
@Entity
@Table(name = "receiver", schema = "public")
@SequenceGenerator(name="receiveridseq",sequenceName="receiver_id_seq")
@JsonDeserialize(using = ReceiverDeserializer.class)
@JsonSerialize(using = ReceiverSerializer.class)
public class Receiver implements java.io.Serializable {

	/**UUID*/
	private static final long serialVersionUID = -943573555355461223L;
	
	private long id;
	private Person person;
	private Set<Message> messages = new HashSet<Message>(0);

	public Receiver() {
	}

	public Receiver(long id, Person person) {
		this.id = id;
		this.person = person;
	}

	public Receiver(long id, Person person, Set<Message> messages) {
		this.id = id;
		this.person = person;
		this.messages = messages;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator="receiveridseq")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ref_person", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "Receiver [id=" + id + ", person=" + person.toString() + "]";
	}

}
