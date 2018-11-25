/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo.dto;

import java.util.Objects;

/**
 *
 * @author OMAR MONTES
 */
public class Asignatura {

    public static final String TABLA = "asignatura";

    private String codAsignatura;

    private Carrera carrera;

    private String nombre;

    private int creditos;

    public Asignatura() {
    }

    public Asignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public Asignatura(String codAsignatura, Carrera carrera, String nombre, int creditos) {
        this.codAsignatura = codAsignatura;
        this.carrera = carrera;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.codAsignatura);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignatura other = (Asignatura) obj;
        if (!Objects.equals(this.codAsignatura, other.codAsignatura)) {
            return false;
        }
        return true;
    }

}
