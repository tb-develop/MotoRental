package fis.project.motorental.model;

import jakarta.persistence.*;

@Entity
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv; // ← dodaj to!

    private String model;
    private String barva;
    private int letnik;
    private double cenaNajema;

    // Getterji in setterji
    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBarva() {
        return barva;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    public int getLetnik() {
        return letnik;
    }

    public void setLetnik(int letnik) {
        this.letnik = letnik;
    }

    public double getCenaNajema() {
        return cenaNajema;
    }

    public void setCenaNajema(double cenaNajema) {
        this.cenaNajema = cenaNajema;
    }
}