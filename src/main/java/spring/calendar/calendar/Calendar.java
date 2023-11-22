package spring.calendar.calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import spring.calendar.eventFile.Event;
import spring.calendar.userFile.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "calendars")
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_id")
	private Long id;

	@NotBlank
	@Column(name = "calendar_name", nullable = false, unique = true)
	@Size (min = 3, max = 50)
	@Pattern (regexp = "^\\S(.*\\S)?", message = "No space allowed at start or end of name")
	private String calendarName;

	@NotBlank
	@Column(name = "calendar_description", nullable = false)
	@Size(min = 3, max = 200)
	@Pattern(regexp = "^\\S(.*\\S)?", message = "No space allowed at start or end of description")
	private String calendarDescription;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "eventCalendar", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Event> events = new HashSet<>();

	public Calendar() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getCalendarDescription() {
		return calendarDescription;
	}

	public void setCalendarDescription(String calendarDescription) {
		this.calendarDescription = calendarDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
}