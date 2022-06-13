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
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MotoristaAPI {

    @Autowired
    MotoristaRepository motoristaRepository;

    @PostMapping("/motoristas")
    public Motorista criarMotoristas(@RequestBody Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    @PutMapping("/motoristas/{id}}")
    public Motorista atualizacaoMotorista(@PathVariable("id") Long id,
                                          @RequestBody Motorista motorista) {
        Motorista motoristaEncontrado =   encontrarMotorista(id);
        motoristaEncontrado.setDataNascimento(motorista.getDataNascimento());
        motoristaEncontrado.setNome(motorista.getNome());
        return motoristaRepository.save(motoristaEncontrado);
    }

    @PatchMapping("/motoristas/{id}")
    public Motorista atualizacaoIncrementalMotorista(@PathVariable("id") Long id,
                                                     @RequestBody Motorista motorista) {
        Motorista encontrarMotorista = encontrarMotorista(id);
        encontrarMotorista.setDataNascimento(Optional.ofNullable(motorista.getDataNascimento())
                .orElse(encontrarMotorista.getDataNascimento()));
        encontrarMotorista.setNome(Optional.ofNullable(motorista.getNome()).orElse(encontrarMotorista.getNome()));
        return motoristaRepository.save(encontrarMotorista);
    }

    @GetMapping("/motoristas")
    public List<Motorista> listarMotoristas() {
        return motoristaRepository.findAll();
    }

    @GetMapping("/motoristas/{id}")
    public Motorista encontrarMotorista(@PathVariable("id") Long id) {
        return motoristaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/motoristas/{id}")
    public void deletarMotorista(@PathVariable("id") Long id) {
        motoristaRepository.deleteById(id);
    }
}
