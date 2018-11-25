/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo.dto;

/**
 *
 * @author OMAR MONTES
 */
public class Tutor {

    public static final String TABLA = "tutor";

    private String codTutor;

    private Alumno alumno;

    private Carrera carrera;

    public Tutor() {
    }

    public Tutor(String codTutor, Alumno alumno, Carrera carrera) {
        this.codTutor = codTutor;
        this.alumno = alumno;
        this.carrera = carrera;
    }

    public String getCodTutor() {
        return codTutor;
    }

    public void setCodTutor(String codTutor) {
        this.codTutor = codTutor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

}
