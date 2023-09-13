package ru.practicum.compilation.model;

import lombok.*;
import ru.practicum.event.model.Event;
import ru.practicum.utils.CompilationSizeConstants;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "compilations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = CompilationSizeConstants.TITLE_SIZE_MAX)
    String title;
    @Column(nullable = false)
    Boolean pinned;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "compilation_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    Set<Event> events;
}
