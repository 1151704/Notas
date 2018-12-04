/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.models.service;

import java.util.List;
import ufps.models.dao.AlumnoImpl;
import ufps.models.dao.AsignaturaImpl;
import ufps.models.dao.CarreraImpl;
import ufps.models.dao.EquivalenteImpl;
import ufps.models.dao.NotaImpl;
import ufps.models.dao.RequisitoImpl;
import ufps.models.dao.TutorImpl;
import ufps.models.entity.Alumno;
import ufps.models.entity.Asignatura;
import ufps.models.entity.Carrera;
import ufps.models.entity.Equivalente;
import ufps.models.entity.Nota;
import ufps.models.entity.Requisito;
import ufps.models.entity.Tutor;

/**
 *
 * @author OMAR MONTES
 */
public class Servicio {

    private CarreraImpl carreraImpl;

    private AsignaturaImpl asignaturaImpl;

    private RequisitoImpl requisitoImpl;

    private AlumnoImpl alumnoImpl;

    private TutorImpl tutorImpl;

    private NotaImpl notaImpl;

    private EquivalenteImpl equivalenteImpl;

    public Servicio() {
        this.carreraImpl = new CarreraImpl();
        this.asignaturaImpl = new AsignaturaImpl();
        this.requisitoImpl = new RequisitoImpl();
        this.alumnoImpl = new AlumnoImpl();
        this.tutorImpl = new TutorImpl();
        this.notaImpl = new NotaImpl();
        this.equivalenteImpl = new EquivalenteImpl();
    }

    public boolean carreraRegistrar(String codigo, String nombre, int semestres, String facultad) {
        return this.carreraImpl.create(new Carrera(codigo, nombre, semestres, facultad));
    }

    public boolean carreraEditar(String codigo, String nombre, int semestres, String facultad) {
        return this.carreraImpl.update(new Carrera(codigo, nombre, semestres, facultad));
    }

    public boolean carreraEliminar(String codigo) {
        return this.carreraImpl.delete(codigo);
    }

    public List<Carrera> carreraListar() {
        return this.carreraImpl.readAll();
    }

    public Carrera carreraDatos(String codigo) {
        return this.carreraImpl.read(codigo);
    }

    public boolean asignaturaRegistrar(String codAsignatura, String codCarrera, String nombre, int creditos) {
        return this.asignaturaImpl.create(new Asignatura(codAsignatura, new Carrera(codCarrera), nombre, creditos));
    }

    public boolean asignaturaEditar(String codAsignatura, String codCarrera, String nombre, int creditos) {
        return this.asignaturaImpl.update(new Asignatura(codAsignatura, new Carrera(codCarrera), nombre, creditos));
    }

    public boolean asignaturaEliminar(String codigo) {
        return this.asignaturaImpl.delete(codigo);
    }

    public List<Asignatura> asignaturaListar() {
        return this.asignaturaImpl.readAll();
    }

    public Asignatura asignaturaDatos(String codigo) {
        return this.asignaturaImpl.read(codigo);
    }

    public boolean requisitoRegistrar(String codRequisito, String codAsignatura, String codCarrera) {
        return this.requisitoImpl.create(new Requisito(codRequisito, new Asignatura(codAsignatura), new Carrera(codCarrera)));
    }

    public boolean requisitoEditar(String codRequisito, String codAsignatura, String codCarrera) {
        return this.requisitoImpl.update(new Requisito(codRequisito, new Asignatura(codAsignatura), new Carrera(codCarrera)));
    }

    public boolean requisitoEliminar(String codigo) {
        return this.requisitoImpl.delete(codigo);
    }

    public List<Requisito> requisitoListar() {
        return this.requisitoImpl.readAll();
    }

    public Requisito requisitoDatos(String codigo) {
        return this.requisitoImpl.read(codigo);
    }

    public boolean alumnoRegistrar(String codAlumno, String codCarrera, String nombre, String apellido, String telefono, String direccion, String email) {
        return this.alumnoImpl.create(new Alumno(codAlumno, new Carrera(codCarrera), nombre, apellido, telefono, direccion, email));
    }

    public boolean alumnoEditar(String codAlumno, String codCarrera, String nombre, String apellido, String telefono, String direccion, String email) {
        return this.alumnoImpl.update(new Alumno(codAlumno, new Carrera(codCarrera), nombre, apellido, telefono, direccion, email));
    }

    public boolean alumnoEliminar(String codAlumno) {
        return this.alumnoImpl.delete(codAlumno);
    }

    public List<Alumno> alumnoListar() {
        return this.alumnoImpl.readAll();
    }

    public Alumno alumnoDatos(String codAlumno) {
        return this.alumnoImpl.read(codAlumno);
    }

    public boolean tutorRegistrar(String codTutor, String codAlumno, String codCarrera) {
        return this.tutorImpl.create(new Tutor(codTutor, new Alumno(codAlumno), new Carrera(codCarrera)));
    }

    public boolean tutorEditar(String codTutor, String codAlumno, String codCarrera) {
        return this.tutorImpl.update(new Tutor(codTutor, new Alumno(codAlumno), new Carrera(codCarrera)));
    }

    public boolean tutorEliminar(String codTutor) {
        return this.tutorImpl.delete(codTutor);
    }

    public List<Tutor> tutorListar() {
        return this.tutorImpl.readAll();
    }

    public Tutor tutorDatos(String codTutor) {
        return this.tutorImpl.read(codTutor);
    }

    public boolean notaRegistrar(String codAsignatura, String codCarrera, String codAlumno, double nota) {
        return this.notaImpl.create(new Nota(new Asignatura(codAsignatura), new Carrera(codCarrera), new Alumno(codAlumno), nota));
    }

    public boolean notaEditar(String codAsignatura, String codCarrera, String codAlumno, double nota) {
        return this.notaImpl.update(new Nota(new Asignatura(codAsignatura), new Carrera(codCarrera), new Alumno(codAlumno), nota));
    }

    public boolean notaEliminar(String codAsignatura, String codCarrera, String codAlumno) {
        return this.notaImpl.delete(new Nota(new Asignatura(codAsignatura), new Carrera(codCarrera), new Alumno(codAlumno), 0));
    }

    public List<Nota> notaListar() {
        return this.notaImpl.readAll();
    }

    public List<Nota> notaListarPorAlumno(String codAlumno) {
        return this.notaImpl.readByAlumno(codAlumno);
    }

    public Nota notaDatos(String codAsignatura, String codCarrera, String codAlumno) {
        return this.notaImpl.read(new Nota(new Asignatura(codAsignatura), new Carrera(codCarrera), new Alumno(codAlumno), 0));
    }

    public boolean equivalenteRegistrar(String codAlumno, String codCarrera, String codAsignatura, String codCarreraMatriculada, String codAsignaturaMatriculada) {
        return this.equivalenteImpl.create(new Equivalente(new Alumno(codAlumno), new Carrera(codCarrera),
                new Asignatura(codAsignatura), new Carrera(codCarreraMatriculada), new Asignatura(codAsignaturaMatriculada)));
    }

    public boolean equivalenteEliminar(String codAlumno, String codCarrera, String codAsignatura, String codCarreraMatriculada, String codAsignaturaMatriculada) {
        return this.equivalenteImpl.delete(new Equivalente(new Alumno(codAlumno), new Carrera(codCarrera),
                new Asignatura(codAsignatura), new Carrera(codCarreraMatriculada), new Asignatura(codAsignaturaMatriculada)));
    }

    public Equivalente equivalenteDato(String codAlumno, String codCarrera, String codAsignatura, String codCarreraMatriculada, String codAsignaturaMatriculada) {
        return this.equivalenteImpl.read(new Equivalente(new Alumno(codAlumno), new Carrera(codCarrera),
                new Asignatura(codAsignatura), new Carrera(codCarreraMatriculada), new Asignatura(codAsignaturaMatriculada)));
    }

    public List<Equivalente> equivalenteListarPorAlumno(String codAlumno) {
        return this.equivalenteImpl.readAllByAlumno(codAlumno);
    }

}
