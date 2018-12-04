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
import ufps.models.entity.Requisito;
import ufps.util.Conexion;

/**
 *
 * @author OMAR MONTES
 */
public class RequisitoImpl extends Conexion implements Crud<Requisito> {

    @Override
    public boolean create(Requisito o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO " + Requisito.TABLA + " (cod_requisito, cod_asignatura, cod_carrera) VALUES (?,?,?)");

            ps.setString(1, o.getCodRequisito());
            ps.setString(2, o.getAsignatura().getCodAsignatura());
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
    public Requisito read(Object key) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        Requisito item = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Requisito.TABLA + " r "
                    + "JOIN asignatura a ON a.cod_asignatura = r.cod_asignatura "
                    + "JOIN carrera c ON c.codigo = r.cod_carrera WHERE "
                    + "r.cod_requisito = ?");

            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            Carrera carrera;
            Asignatura asignatura;

            while (rs.next()) {
                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), 0, null);
                asignatura = new Asignatura(rs.getString("a.cod_asignatura"), null, rs.getString("a.nombre"), 0);
                item = new Requisito(rs.getString("r.cod_requisito"), asignatura, carrera);
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
    public List<Requisito> readAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Requisito> listado = new ArrayList<>();
        Requisito item;
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + Requisito.TABLA + " r "
                    + "JOIN asignatura a ON a.cod_asignatura = r.cod_asignatura "
                    + "JOIN carrera c ON c.codigo = r.cod_carrera ");

            rs = ps.executeQuery();

            Carrera carrera;
            Asignatura asignatura;

            while (rs.next()) {
                carrera = new Carrera(rs.getString("c.codigo"), rs.getString("c.nombre"), 0, null);
                asignatura = new Asignatura(rs.getString("a.cod_asignatura"), null, rs.getString("a.nombre"), 0);
                item = new Requisito(rs.getString("r.cod_requisito"), asignatura, carrera);

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
    public boolean update(Requisito o) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + Requisito.TABLA + " SET cod_asignatura = ?, cod_carrera = ? WHERE cod_requisito  =?");

            ps.setString(1, o.getAsignatura().getCodAsignatura());
            ps.setString(2, o.getCarrera().getCodigo());
            ps.setString(3, o.getCodRequisito());

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
            ps = con.prepareStatement("DELETE FROM " + Requisito.TABLA + " WHERE cod_requisito  =?");

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
