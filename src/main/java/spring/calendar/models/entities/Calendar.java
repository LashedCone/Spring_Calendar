package spring.calendar.models.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import spring.calendar.models.IdClass;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "calendars")
public class Calendar extends IdClass {
	@NotBlank
	@Column(name = "calendar_name", nullable = false, unique = true)
	@Size(min = 3, max = 50)
	private String calendarName;

	@NotBlank
	@Column(name = "calendar_description", nullable = false)
	@Size(min = 3, max = 200)
	private String calendarDescription;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	@NotNull
	private User user;

	@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Event> events = new HashSet<>();

	public Calendar() {}

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