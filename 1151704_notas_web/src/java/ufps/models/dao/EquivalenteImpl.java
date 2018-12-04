/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ufps.models.entity.Alumno;
import ufps.models.entity.Asignatura;
import ufps.models.entity.Carrera;
import ufps.models.entity.Crud;
import ufps.models.entity.Equivalente;
import ufps.util.Conexion;

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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equivalente item = null;
        Alumno alumno;
        Carrera carrera;
        Carrera carreraM;
        Asignatura asignatura;
        Asignatura asignaturaM;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Equivalente.TABLA + " n "
                    + "JOIN " + Alumno.TABLA + " a ON a.cod_alumno = n.cod_alumno "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = n.cod_carrera "
                    + "JOIN " + Carrera.TABLA + " cm ON cm.codigo = n.cod_carrera_mat "
                    + "JOIN " + Asignatura.TABLA + " m ON m.cod_asignatura = n.cod_asignatura "
                    + "JOIN " + Asignatura.TABLA + " mm ON mm.cod_asignatura = n.cod_asignatura_mat "
                    + "WHERE n.cod_alumno = ? AND n.cod_carrera = ? AND n.cod_asignatura =? and n.cod_carrera_mat = ? AND n.cod_asignatura_mat = ? ");

            Equivalente equivalente = (Equivalente) key;

            ps.setString(1, equivalente.getAlumno().getCodAlumno());
            ps.setString(2, equivalente.getCarrera().getCodigo());
            ps.setString(3, equivalente.getAsignatura().getCodAsignatura());
            ps.setString(4, equivalente.getCarreraMatriculada().getCodigo());
            ps.setString(5, equivalente.getAsignaturaMatriculada().getCodAsignatura());

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                carreraM = new Carrera(rs.getString("cm.codigo"), rs.getString("cm.nombre"), rs.getInt("cm.semestres"), rs.getString("cm.facultad"));
                asignatura = new Asignatura(rs.getString("m.cod_asignatura"), null, rs.getString("m.nombre"), 0);
                asignaturaM = new Asignatura(rs.getString("mm.cod_asignatura"), null, rs.getString("mm.nombre"), 0);

                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

                item = new Equivalente(alumno, carrera, asignatura, carreraM, asignaturaM);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }
        return (item);
    }

    @Override
    public List<Equivalente> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Equivalente> readAllByAlumno(String cod_alumno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Equivalente> listado = new ArrayList<>();
        Alumno alumno;
        Carrera carrera;
        Carrera carreraM;
        Asignatura asignatura;
        Asignatura asignaturaM;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Equivalente.TABLA + " n "
                    + "JOIN " + Alumno.TABLA + " a ON a.cod_alumno = n.cod_alumno "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = n.cod_carrera "
                    + "JOIN " + Carrera.TABLA + " cm ON cm.codigo = n.cod_carrera_mat "
                    + "JOIN " + Asignatura.TABLA + " m ON m.cod_asignatura = n.cod_asignatura "
                    + "JOIN " + Asignatura.TABLA + " mm ON mm.cod_asignatura = n.cod_asignatura_mat "
                    + "WHERE n.cod_alumno = ? ");


            ps.setString(1, cod_alumno);

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                carreraM = new Carrera(rs.getString("cm.codigo"), rs.getString("cm.nombre"), rs.getInt("cm.semestres"), rs.getString("cm.facultad"));
                asignatura = new Asignatura(rs.getString("m.cod_asignatura"), null, rs.getString("m.nombre"), 0);
                asignaturaM = new Asignatura(rs.getString("mm.cod_asignatura"), null, rs.getString("mm.nombre"), 0);

                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

                listado.add(new Equivalente(alumno, carrera, asignatura, carreraM, asignaturaM));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }
        return (listado);
    }

    @Override
    public boolean update(Equivalente o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object key) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + Equivalente.TABLA + " "
                    + "WHERE cod_alumno = ? AND cod_carrera =? AND cod_asignatura = ? AND cod_carrera_mat =? AND cod_asignatura_mat = ? ");

            Equivalente equivalente = (Equivalente) key;

            ps.setString(1, equivalente.getAlumno().getCodAlumno());
            ps.setString(2, equivalente.getCarrera().getCodigo());
            ps.setString(3, equivalente.getAsignatura().getCodAsignatura());
            ps.setString(4, equivalente.getCarreraMatriculada().getCodigo());
            ps.setString(5, equivalente.getAsignaturaMatriculada().getCodAsignatura());

            if (ps.executeUpdate() > 0) {
                return (true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return (false);
    }

    @Override
    public int auto_increment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
