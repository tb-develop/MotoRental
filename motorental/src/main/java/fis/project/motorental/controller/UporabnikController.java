package fis.project.motorental.controller;

import fis.project.motorental.model.Uporabnik;
import fis.project.motorental.repository.UporabnikRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081") // ✅ Vue dostop
@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    private final UporabnikRepository uporabnikRepository;

    public UporabnikController(UporabnikRepository uporabnikRepository) {
        this.uporabnikRepository = uporabnikRepository;
    }

    @GetMapping
    public List<Uporabnik> getAll() {
        return uporabnikRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Uporabnik uporabnik) {
        // Validacija imena - le črke in presledki (slovenska šumniki vključeni)
        if (uporabnik.getImePriimek() == null || !uporabnik.getImePriimek().matches("^[A-Za-zČčŠšŽžĆćĐđ ]+$")) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Ime in priimek ne smeta vsebovati številk ali posebnih znakov.");
        }

        // Validacija starosti
        if (uporabnik.getDatumRojstva() == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Datum rojstva je obvezen.");
        }

        int starost = Period.between(uporabnik.getDatumRojstva(), LocalDate.now()).getYears();
        if (starost < 24) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Uporabnik mora biti star vsaj 24 let.");
        }

        Uporabnik shranjen = uporabnikRepository.save(uporabnik);
        return ResponseEntity.ok(shranjen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Uporabnik> getById(@PathVariable Long id) {
        return uporabnikRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!uporabnikRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        uporabnikRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}