/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ufps.modelo.util.Servicio;

/**
 *
 * @author OMAR MONTES
 */
public class RequisitoEditar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        session = req.getSession();
        Servicio controlador = (Servicio) session.getAttribute("controlador");

        String codigo = req.getParameter("codigo");
        String asignatura = req.getParameter("asignatura");
        String carrera = req.getParameter("carrera");

        if (controlador != null && codigo != null && asignatura != null && carrera != null) {

            controlador.requisitoEditar(codigo, asignatura, carrera);

        }

        resp.sendRedirect(req.getContextPath() + "/requisitos.jsp");
    }

}
