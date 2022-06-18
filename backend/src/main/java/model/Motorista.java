package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity
public class Motorista {

    @Id
    @GeneratedValue
    Long id;
    String nome;
    Date dataNascimento;
}