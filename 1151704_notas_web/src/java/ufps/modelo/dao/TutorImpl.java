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
import ufps.modelo.dto.Carrera;
import ufps.modelo.dto.Tutor;
import ufps.modelo.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class TutorImpl extends Conexion implements Crud<Tutor> {

    @Override
    public boolean create(Tutor o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Tutor.TABLA + " (cod_tutor, cod_alumno, cod_carrera) VALUES (?,?,?)");

            ps.setString(1, o.getCodTutor());
            ps.setString(2, o.getAlumno().getCodAlumno());
            ps.setString(3, o.getCarrera().getCodigo());

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
    public Tutor read(Object key) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Tutor item = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Tutor.TABLA + " t "
                    + "JOIN alumno a ON a.cod_alumno = t.cod_alumno "
                    + "JOIN carrera c ON c.codigo = t.cod_carrera WHERE "
                    + "t.cod_tutor = ?");

            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            Carrera carrera;
            Alumno alumno;

            while (rs.next()) {
                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), 0, null);
                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"), null, null, null);
                item = new Tutor(rs.getString("t.cod_tutor"), alumno, carrera);
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
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return item;
    }

    @Override
    public List<Tutor> readAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Tutor> listado = new ArrayList<>();
        Tutor item;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Tutor.TABLA + " t "
                    + "JOIN alumno a ON a.cod_alumno = t.cod_alumno "
                    + "JOIN carrera c ON c.codigo = t.cod_carrera ");

            rs = ps.executeQuery();

            Carrera carrera;
            Alumno alumno;

            while (rs.next()) {
                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), 0, null);
                alumno = new Alumno(rs.getString("a.cod_alumno"), null, rs.getString("a.nombre"), rs.getString("a.apellido"), null, null, null);
                item = new Tutor(rs.getString("t.cod_tutor"), alumno, carrera);

                listado.add(item);
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
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return listado;
    }

    @Override
    public boolean update(Tutor o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Tutor.TABLA + " SET cod_alumno = ?, cod_carrera= ? WHERE cod_tutor  =?");

            ps.setString(1, o.getAlumno().getCodAlumno());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getCodTutor());

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
            ps = con.prepareStatement("DELETE FROM " + Tutor.TABLA + " WHERE cod_tutor  =?");

            ps.setString(1, key.toString());

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
