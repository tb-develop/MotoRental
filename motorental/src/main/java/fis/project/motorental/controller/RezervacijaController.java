package fis.project.motorental.controller;

import fis.project.motorental.model.Rezervacija;
import fis.project.motorental.repository.RezervacijaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081") // ✅ omogoči dostop iz Vue frontenda
@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaRepository rezervacijaRepository;

    public RezervacijaController(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @GetMapping
    public List<Rezervacija> getAll() {
        return rezervacijaRepository.findAll();
    }

    @PostMapping
    public Rezervacija create(@RequestBody Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    @GetMapping("/aktivne")
    public List<Rezervacija> getAktivneRezervacije() {
        return rezervacijaRepository.findAll(); // ali dodaš filter npr. .findByAktivnaTrue()
    }

    @GetMapping("/{id}")
    public Rezervacija getById(@PathVariable Long id) {
        return rezervacijaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Rezervacija update(@PathVariable Long id, @RequestBody Rezervacija updated) {
        Rezervacija r = rezervacijaRepository.findById(id).orElse(null);
        if (r == null) return null;

        r.setDatumZacetka(updated.getDatumZacetka());
        r.setDatumKonca(updated.getDatumKonca());
        r.setMotor(updated.getMotor());
        r.setUporabnik(updated.getUporabnik());

        return rezervacijaRepository.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rezervacijaRepository.deleteById(id);
    }
}