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
public class Equivalente {

    public static final String TABLA = "equivalente";

    private Alumno alumno;

    private Carrera carrera;

    private Asignatura asignatura;

    private Carrera carreraMatriculada;

    private Asignatura asignaturaMatriculada;

    public Equivalente() {
    }

    public Equivalente(Alumno alumno, Carrera carrera, Asignatura asignatura, Carrera carreraMatriculada, Asignatura asignaturaMatriculada) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.asignatura = asignatura;
        this.carreraMatriculada = carreraMatriculada;
        this.asignaturaMatriculada = asignaturaMatriculada;
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

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Carrera getCarreraMatriculada() {
        return carreraMatriculada;
    }

    public void setCarreraMatriculada(Carrera carreraMatriculada) {
        this.carreraMatriculada = carreraMatriculada;
    }

    public Asignatura getAsignaturaMatriculada() {
        return asignaturaMatriculada;
    }

    public void setAsignaturaMatriculada(Asignatura asignaturaMatriculada) {
        this.asignaturaMatriculada = asignaturaMatriculada;
    }

}
