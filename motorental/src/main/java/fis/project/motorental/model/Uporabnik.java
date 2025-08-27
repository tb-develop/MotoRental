package fis.project.motorental.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imePriimek;
    private String naslov;
    private LocalDate datumRojstva;
    private boolean vozniski;
    

    public Uporabnik() {
    }

    public Uporabnik(String imePriimek, String naslov, LocalDate datumRojstva, boolean vozniski) {
        this.imePriimek = imePriimek;
        this.naslov = naslov;
        this.datumRojstva = datumRojstva;
        this.vozniski = vozniski;
    }

    public Long getId() {
        return id;
    }

    public String getImePriimek() {
        return imePriimek;
    }

    public void setImePriimek(String imePriimek) {
        this.imePriimek = imePriimek;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public LocalDate getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(LocalDate datumRojstva) {
        this.datumRojstva = datumRojstva;
    }

    public boolean isVozniski() {
        return vozniski;
    }

    public void setVozniski(boolean vozniski) {
        this.vozniski = vozniski;
    }
}