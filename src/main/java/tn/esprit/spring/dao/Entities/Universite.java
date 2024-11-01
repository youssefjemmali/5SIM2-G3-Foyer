package tn.esprit.spring.dao.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_UNIVERSITE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;
    String nomUniversite;
    String adresse;

    @Enumerated(EnumType.STRING)
    TypeUniversite typeUniversite;

    @OneToOne(cascade = CascadeType.ALL) //ajout, Modif et supprim
    Foyer foyer;

    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    List<Programme> programmes;

}
