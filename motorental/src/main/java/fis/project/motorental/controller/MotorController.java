package fis.project.motorental.controller;

import fis.project.motorental.model.Motor;
import fis.project.motorental.repository.MotorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081") // omogoča povezavo s frontendom na localhost:8081
@RestController
@RequestMapping("/motorji")
public class MotorController {

    private final MotorRepository motorRepository;

    public MotorController(MotorRepository motorRepository) {
        this.motorRepository = motorRepository;
    }

    @GetMapping
    public List<Motor> getAll() {
        return motorRepository.findAll();
    }

    @PostMapping
    public Motor create(@RequestBody Motor motor) {
        return motorRepository.save(motor);
    }

    @GetMapping("/{id}")
    public Motor getById(@PathVariable Long id) {
        return motorRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Motor update(@PathVariable Long id, @RequestBody Motor updatedMotor) {
        Motor motor = motorRepository.findById(id).orElse(null);
        if (motor == null) return null;

        motor.setModel(updatedMotor.getModel());
        motor.setBarva(updatedMotor.getBarva());
        motor.setLetnik(updatedMotor.getLetnik());
        motor.setCenaNajema(updatedMotor.getCenaNajema());

        return motorRepository.save(motor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        motorRepository.deleteById(id);
    }
}