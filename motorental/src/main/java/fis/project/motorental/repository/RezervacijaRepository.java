package fis.project.motorental.repository;

import fis.project.motorental.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
}