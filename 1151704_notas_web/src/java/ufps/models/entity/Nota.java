/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.models.entity;

/**
 *
 * @author OMAR MONTES
 */
public class Nota {

    public static final String TABLA = "nota";

    private Asignatura asignatura;

    private Carrera carrera;

    private Alumno alumno;

    private double nota;

    public Nota() {
    }

    public Nota(Asignatura asignatura, Carrera carrera, Alumno alumno, double nota) {
        this.asignatura = asignatura;
        this.carrera = carrera;
        this.alumno = alumno;
        this.nota = nota;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

}
