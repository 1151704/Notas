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
import ufps.modelo.dto.Carrera;
import ufps.modelo.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class CarreraImpl extends Conexion implements Crud<Carrera> {

    @Override
    public boolean create(Carrera o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Carrera.TABLA + " (codigo, nombre, semestres, facultad) VALUES (?,?,?,?)");

            ps.setString(1, o.getCodigo());
            ps.setString(2, o.getNombre());
            ps.setInt(3, o.getSemestres());
            ps.setString(4, o.getFacultad());

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
            }
        }

        return false;
    }

    @Override
    public Carrera read(Object key) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Carrera item = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Carrera.TABLA + " where codigo = ?");

            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                item = new Carrera(rs.getString("codigo"), rs.getString("nombre"), rs.getInt("semestres"), rs.getString("facultad"));

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
    public List<Carrera> readAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Carrera> listado = new ArrayList();

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Carrera.TABLA + " ORDER BY nombre");

            rs = ps.executeQuery();

            while (rs.next()) {

                listado.add(new Carrera(rs.getString("codigo"), rs.getString("nombre"), rs.getInt("semestres"), rs.getString("facultad")));

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
    public boolean update(Carrera o) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Carrera.TABLA + " SET nombre=?, semestres=?, facultad=? WHERE codigo = ?");

            ps.setString(1, o.getNombre());
            ps.setInt(2, o.getSemestres());
            ps.setString(3, o.getFacultad());
            ps.setString(4, o.getCodigo());

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
            ps = con.prepareStatement("DELETE FROM " + Carrera.TABLA + " WHERE codigo = ?");

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
            }
        }

        return false;
    }

    @Override
    public int auto_increment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
