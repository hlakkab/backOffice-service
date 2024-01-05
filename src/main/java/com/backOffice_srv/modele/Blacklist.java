package com.backOffice_srv.modele;

import com.backOffice_srv.modele.listeners.AgentListener;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @CreationTimestamp
    @Column(updatable = false)
    protected Instant createdAt;

    @NotNull
    @Column(unique = true)
    private Integer idClient;

    @NotNull
    private String motif;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String email;

    @NotNull
    private Boolean archived = false ;

}
