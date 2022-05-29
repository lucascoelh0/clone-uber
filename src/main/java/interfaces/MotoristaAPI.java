package interfaces;

import model.Motorista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import repository.MotoristaRepository;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MotoristaAPI {

    @Autowired
    MotoristaRepository motoristaRepository;

    @GetMapping("/motoristas")
    public List<Motorista> listMotoristas() {
        return motoristaRepository.findAll();
    }
    
    @GetMapping("/motoristas/{id}")
    public Motorista encontrarMotorista(@PathVariable("id") Long id) {
        return motoristaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/motoristas")
    public Motorista criarMotorista(@RequestBody Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    @PutMapping("/motoristas/{id}")
    public Motorista atualizacaoCompletaMotorista (@PathVariable("id") long Id,
                                                   @RequestBody Motorista motorista) {

        Motorista   encontrarMotorista =  encontrarMotorista(id);
        encontrarMotorista.setNome(motorista.getNome());
        encontrarMotorista.setDataNascimento(motorista.getDataNascimento());

        return motoristaRepository.save(encontrarMotorista);
    }

    @PatchMapping("/motoristas/{id}")
    public Motorista atualizacaoIncrementalMotorista(@PathVariable("id") Long id,
                                                     @RequestBody Motorista motorista) {

        Motorista encontrarMotorista = encontrarMotorista(id);
        encontrarMotorista.setDataNascimento(Optional.ofNullable(motorista.getDataNascimento()).
                orElse(encontrarMotorista.getDataNascimento()));
        encontrarMotorista.setNome(Optional.ofNullable(motorista.getNome()).
                orElse(encontrarMotorista.getNome()));

        return motoristaRepository.save(encontrarMotorista);
    }

    @DeleteMapping("/motoristas/{id}")
    public void deletarMotorista(@PathVariable("id") Long id ) {
        motoristaRepository.delete(encontrarMotorista(id));
    }
}
