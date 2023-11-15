package spring.calendar.models;

import jakarta.persistence.*;

@MappedSuperclass
public class IdClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public IdClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}