package model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Passageiro {

    @Id
    @GeneratedValue
    Long id;
    String nome;
}