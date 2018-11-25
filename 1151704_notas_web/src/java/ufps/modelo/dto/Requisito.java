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
public class Requisito {

    public static final String TABLA = "requisito";

    private String codRequisito;

    private Asignatura asignatura;

    private Carrera carrera;

    public Requisito() {
    }

    public Requisito(String codRequisito, Asignatura asignatura, Carrera carrera) {
        this.codRequisito = codRequisito;
        this.asignatura = asignatura;
        this.carrera = carrera;
    }

    public String getCodRequisito() {
        return codRequisito;
    }

    public void setCodRequisito(String codRequisito) {
        this.codRequisito = codRequisito;
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

}
