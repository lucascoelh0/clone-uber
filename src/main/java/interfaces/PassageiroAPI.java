package interfaces;

import model.Passageiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import repository.PassageiroRepository;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping(path ="/passageiro", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassageiroAPI {

    @Autowired
    PassageiroRepository passageiroRepository;

    @GetMapping()
    public List<Passageiro> listarPassageiro() {
        return passageiroRepository.findAll();
    }

    @GetMapping("/{id}")
    public  Passageiro encontrarPassageiro(@PathVariable("id") Long id) {
        return passageiroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ConditionalOnProperty
    public Passageiro criarPassageiro(@RequestBody Passageiro passageiro) {
        return passageiroRepository.save(passageiro);
    }

    @PutMapping("/{id}")
    public Passageiro atulizacaCompletaPassageiro(@PathVariable("id") Long id , @RequestBody Passageiro passageiro) {
        Passageiro passageiroAtualizar = encontrarPassageiro(id);
        passageiroAtualizar.setNome(passageiro.getNome());
        return passageiroRepository.save(passageiroAtualizar);
    }

    @PatchMapping("/{id}")
    public Passageiro atualizacaoIncrementalPassageiros( @PathVariable("id") Long id, @RequestBody Passageiro passageiro) {
        Passageiro encontrarPassageiro =  encontrarPassageiro(id);
        encontrarPassageiro.setNome(Optional.ofNullable(passageiro.getNome()).orElse(encontrarPassageiro.getNome()));
        return passageiroRepository.save(encontrarPassageiro);
    }

    @DeleteMapping("/{id}")
    public void deletaPassageiro(@PathVariable("id") Long id) {
        passageiroRepository.delete(encontrarPassageiro(id));
    }
}