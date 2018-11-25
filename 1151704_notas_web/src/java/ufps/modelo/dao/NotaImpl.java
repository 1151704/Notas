/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ufps.modelo.crud.Crud;
import ufps.modelo.dto.Alumno;
import ufps.modelo.dto.Asignatura;
import ufps.modelo.dto.Carrera;
import ufps.modelo.dto.Nota;
import ufps.modelo.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class NotaImpl extends Conexion implements Crud<Nota> {

    @Override
    public boolean create(Nota o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Nota.TABLA + " (cod_asignatura, cod_carrera, cod_alumno, nota) VALUES (?,?,?,?)");

            ps.setString(1, o.getAsignatura().getCodAsignatura());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getAlumno().getCodAlumno());
            ps.setDouble(4, o.getNota());

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

        return false;
    }

    @Override
    public Nota read(Object key) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Nota item = null;
        Carrera carrera;
        Alumno alumno;
        Asignatura asignatura;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Nota.TABLA + " n "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = n.cod_carrera "
                    + "JOIN " + Alumno.TABLA + " a ON a.cod_alumno = n.cod_alumno "
                    + "JOIN " + Asignatura.TABLA + " m ON m.cod_asignatura = n.cod_asignatura "
                    + "WHERE n.cod_carrera = ? AND n.cod_alumno = ? AND c.cod_asignatura =? ");

            Nota nota = (Nota) key;

            ps.setString(1, nota.getCarrera().getCodigo());
            ps.setString(2, nota.getAlumno().getCodAlumno());
            ps.setString(3, nota.getAsignatura().getCodAsignatura());

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                asignatura = new Asignatura(rs.getString("m.cod_asignatura"), null, rs.getString("m.nombre"), 0);

                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

                item = new Nota(asignatura, carrera, alumno, rs.getDouble("n.nombre"));

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
    public List<Nota> readAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Nota> listado = new ArrayList<>();
        Nota item;
        Carrera carrera;
        Alumno alumno;
        Asignatura asignatura;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Nota.TABLA + " n "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = n.cod_carrera "
                    + "JOIN " + Alumno.TABLA + " a ON a.cod_alumno = n.cod_alumno "
                    + "JOIN " + Asignatura.TABLA + " m ON m.cod_asignatura = n.cod_asignatura ");

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                asignatura = new Asignatura(rs.getString("m.cod_asignatura"), null, rs.getString("m.nombre"), 0);

                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

                item = new Nota(asignatura, carrera, alumno, rs.getDouble("n.nombre"));

                listado.add(item);
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

    public List<Nota> readByAlumno(String codAlumno) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Nota> listado = new ArrayList<>();
        Nota item;
        Carrera carrera;
        Asignatura asignatura;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Nota.TABLA + " n "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = n.cod_carrera "
                    + "JOIN " + Asignatura.TABLA + " m ON m.cod_asignatura = n.cod_asignatura "
                    + "WHERE n.cod_alumno = ? ");

            ps.setString(1, codAlumno);

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                asignatura = new Asignatura(rs.getString("m.cod_asignatura"), null, rs.getString("m.nombre"), 0);

                item = new Nota(asignatura, carrera, null, rs.getDouble("n.nota"));

                listado.add(item);
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
    public boolean update(Nota o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Nota.TABLA + " SET nota = ? WHERE cod_asignatura = ? AND cod_carrera = ? AND cod_alumno = ?");

            ps.setDouble(1, o.getNota());
            ps.setString(2, o.getAsignatura().getCodAsignatura());
            ps.setString(3, o.getCarrera().getCodigo());
            ps.setString(4, o.getAlumno().getCodAlumno());

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

        return false;
    }

    @Override
    public boolean delete(Object key) {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + Nota.TABLA + " WHERE cod_asignatura = ? AND cod_carrera =? AND cod_alumno = ?");

            Nota nota = (Nota) key;

            ps.setString(1, nota.getAsignatura().getCodAsignatura());
            ps.setString(2, nota.getCarrera().getCodigo());
            ps.setString(3, nota.getAlumno().getCodAlumno());

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

        return false;
    }

    @Override
    public int auto_increment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
