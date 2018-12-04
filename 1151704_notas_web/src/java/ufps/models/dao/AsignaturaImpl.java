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
import ufps.models.entity.Crud;
import ufps.models.entity.Asignatura;
import ufps.models.entity.Carrera;
import ufps.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class AsignaturaImpl extends Conexion implements Crud<Asignatura> {

    @Override
    public boolean create(Asignatura o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Asignatura.TABLA + " (cod_asignatura, cod_carrera, nombre, creditos) VALUES (?,?,?,?)");

            ps.setString(1, o.getCodAsignatura());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getNombre());
            ps.setInt(4, o.getCreditos());

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
    public Asignatura read(Object key) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Asignatura item = null;
        Carrera carrera;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Asignatura.TABLA + " a "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = a.cod_carrera WHERE a.cod_alumno = ?");

            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));
                
                item = new Asignatura(rs.getString("a.cod_asignatura"), carrera, rs.getString("a.nombre"), rs.getInt("a.creditos"));

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
    public List<Asignatura> readAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Asignatura> listado = new ArrayList();
        Asignatura item;
        Carrera carrera;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Asignatura.TABLA + " a "
                    + "JOIN " + Carrera.TABLA + " c ON c.codigo = a.cod_carrera "
                    + "ORDER BY c.nombre, a.nombre");

            rs = ps.executeQuery();

            while (rs.next()) {

                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), rs.getInt("c.semestres"), rs.getString("c.facultad"));

                item = new Asignatura(rs.getString("a.cod_asignatura"), carrera, rs.getString("a.nombre"), rs.getInt("a.creditos"));

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
    public boolean update(Asignatura o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Asignatura.TABLA + " SET cod_carrera=?, nombre=?, creditos=? WHERE cod_asignatura = ?");

            ps.setString(1, o.getCarrera().getCodigo());
            ps.setString(2, o.getNombre());
            ps.setInt(3, o.getCreditos());
            ps.setString(4, o.getCodAsignatura());

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
            ps = con.prepareStatement("DELETE FROM " + Asignatura.TABLA + " WHERE cod_asignatura = ?");

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
