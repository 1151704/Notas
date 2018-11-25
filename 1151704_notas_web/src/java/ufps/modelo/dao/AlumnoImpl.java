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
import ufps.modelo.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class AlumnoImpl extends Conexion implements Crud<Alumno> {

    @Override
    public boolean create(Alumno o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Alumno.TABLA + " (cod_alumno, cod_carrera, nombre, apellido, direccion, telefono, email) VALUES (?,?,?,?,?,?,?)");

            ps.setString(1, o.getCodAlumno());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getNombre());
            ps.setString(4, o.getApellido());
            ps.setString(5, o.getDireccion());
            ps.setString(6, o.getTelefono());
            ps.setString(7, o.getEmail());

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
    public Alumno read(Object key) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno item = null;
        Carrera carrera;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Alumno.TABLA + " a "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = a.cod_carrera WHERE a.cod_alumno = ?");

            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));

                item = new Alumno(rs.getString("a.cod_alumno"), carrera, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

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
    public List<Alumno> readAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Alumno> listado = new ArrayList<>();
        Alumno item;
        Carrera carrera;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Alumno.TABLA + " a "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = a.cod_carrera");

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));

                item = new Alumno(rs.getString("a.cod_alumno"), carrera, rs.getString("a.nombre"), rs.getString("a.apellido"),
                        rs.getString("a.telefono"), rs.getString("a.direccion"), rs.getString("a.email"));

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
    public boolean update(Alumno o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Alumno.TABLA + " SET cod_carrera=?, nombre=?, apellido=?, direccion=?, telefono=?, email=? WHERE cod_alumno = ?");

            ps.setString(1, o.getCarrera().getCodigo());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getApellido());
            ps.setString(4, o.getDireccion());
            ps.setString(5, o.getTelefono());
            ps.setString(6, o.getEmail());
            ps.setString(7, o.getCodAlumno());

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
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + Alumno.TABLA + " WHERE cod_alumno = ?");

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
