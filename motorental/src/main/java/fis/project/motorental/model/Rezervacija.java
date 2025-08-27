package fis.project.motorental.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumZacetka;
    private LocalDate datumKonca;

    @ManyToOne
    @JoinColumn(name = "motor_id")
    @JsonIgnoreProperties({"rezervacije"})
    private Motor motor;

    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    @JsonIgnoreProperties({"rezervacije"})
    private Uporabnik uporabnik;

    // Getterji in setterji
    public Long getId() {
        return id;
    }

    public LocalDate getDatumZacetka() {
        return datumZacetka;
    }

    public void setDatumZacetka(LocalDate datumZacetka) {
        this.datumZacetka = datumZacetka;
    }

    public LocalDate getDatumKonca() {
        return datumKonca;
    }

    public void setDatumKonca(LocalDate datumKonca) {
        this.datumKonca = datumKonca;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }
}