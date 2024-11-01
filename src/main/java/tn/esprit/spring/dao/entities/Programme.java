package tn.esprit.spring.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "T_PROGRAMME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Programme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idProgramme;

    String nomProgramme;
    int dureeEnAnnees; // durée du programme en années
    String niveau; // ex: Licence, Master, Ingénieur, Doctorat
    
    @ManyToOne
    Universite universite;
}
