/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import ufps.modelo.crud.Crud;
import ufps.modelo.dto.Equivalente;
import ufps.modelo.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class EquivalenteImpl extends Conexion implements Crud<Equivalente> {

    @Override
    public boolean create(Equivalente o) {
        PreparedStatement ps;
        try {
            ps = super.getConnection().prepareStatement("INSERT INTO " + Equivalente.TABLA + " (cod_asignatura, cod_carrera, cod_alumno, cod_carrera_mat, cod_asignatura_mat) VALUES (?,?,?,?,?)");

            ps.setString(1, o.getAsignatura().getCodAsignatura());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getAlumno().getCodAlumno());
            ps.setString(4, o.getCarreraMatriculada().getCodigo());
            ps.setString(5, o.getAsignaturaMatriculada().getCodAsignatura());

            if (ps.executeUpdate() > 0) {
                return (true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Equivalente read(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Equivalente> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Equivalente o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int auto_increment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
