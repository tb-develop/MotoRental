package fis.project.motorental.repository;

import fis.project.motorental.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorRepository extends JpaRepository<Motor, Long> {
}