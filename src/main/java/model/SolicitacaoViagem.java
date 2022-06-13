package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class SolicitacaoViagem {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Passageiro passageiro;
    String origem;
    String destino;
}