package spring.calendar.eventFile.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import spring.calendar.calendar.model.entity.Calendar;
import spring.calendar.userFile.model.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "event_name", nullable = false)
	@Size (min = 3, max = 50)
	@Pattern (regexp = "^\\S(.*\\S)?", message = "No space allowed at start or end of name")
	private String eventName;

	@Column(name = "event_description")
	@Size(min = 3, max = 200)
	@Pattern(regexp = "^\\S(.*\\S)?", message = "No space allowed at start or end of description")
	private String eventDescription;

	@Column(name = "event_start")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@DateTimeFormat (pattern = "dd-MM-yyyy HH:mm")
	/*shape = JsonFormat.Shape.STRING
	Questa annotazione permette di controllare la rappresentazione formattata
	dei campi di una classe quando vengono convertiti da e verso JSON*/
	private LocalDateTime eventStart;

	@Column(name = "event_end")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@DateTimeFormat (pattern = "dd-MM-yyyy HH:mm")
	/*Entrambe le annotazioni hanno scopi diversi e vengono utilizzate in contesti diversi.
	Ad esempio, se stai scrivendo un controller REST per ricevere input da un client
	(ad esempio tramite un modulo web o richieste POST di Postman), potresti utilizzare
	@DateTimeFormat per controllare il formato delle date ricevute.
	D'altra parte, quando restituisci dati JSON da un controller REST,
	puoi utilizzare @JsonFormat per controllare il formato delle date nell'output JSON.*/
	private LocalDateTime eventEnd;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_event", joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> usersList = new HashSet<>();

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "calendar_id", nullable = false)
	private Calendar eventCalendar;

	public Event() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public LocalDateTime getEventStart() {
		return eventStart;
	}

	public void setEventStart(LocalDateTime eventStart) {
		this.eventStart = eventStart;
	}

	public LocalDateTime getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(LocalDateTime eventEnd) {
		this.eventEnd = eventEnd;
	}

	public Set<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(Set<User> usersList) {
		this.usersList = usersList;
	}

	public Calendar getEventCalendar() {
		return eventCalendar;
	}

	public void setEventCalendar(Calendar eventCalendar) {
		this.eventCalendar = eventCalendar;
	}
}
