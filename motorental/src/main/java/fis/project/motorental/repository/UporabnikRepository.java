package fis.project.motorental.repository;

import fis.project.motorental.model.Uporabnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {
}