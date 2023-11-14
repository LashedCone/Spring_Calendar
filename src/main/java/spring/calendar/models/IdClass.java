package spring.calendar.models;

import jakarta.persistence.*;

@MappedSuperclass
public class IdClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
