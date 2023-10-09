/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uts.edu.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.edu.java.ejb.PersonaFacade;
import uts.edu.java.ejb.TelefonoFacade;
import uts.edu.java.modelo.Persona;
import uts.edu.java.modelo.Telefono;

/**
 *
 * @author cuent
 */
public class TelefonoControlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private PersonaFacade personaFacade;
    @EJB
    private TelefonoFacade telefonoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String rut = request.getParameter("rut");
        String telefono = request.getParameter("phone");
        String action = request.getParameter("action");
        String selectedNumber = request.getParameter("selectedPhone");
        int obtenerUltimoId = telefonoFacade.obtenerUltimoId();
        String message;
        boolean IsFound = false;
        boolean isCreate = false;
        int id = 0;
        
        
        Persona persona2 = new Persona();
        System.out.println("Persona" + persona2.getRut());
        Persona persona = personaFacade.searchPersona(rut);

        Telefono telefono2 = new Telefono(obtenerUltimoId + 1, telefono, persona);

        // Realiza la búsqueda de la persona
        try {
            if ("search".equalsIgnoreCase(action)) {
                if (persona != null) {
                    IsFound = true;
                    if (IsFound) {
                        List<Telefono> telefonos = telefonoFacade.obtenerTelefonosDePersona(rut);
                        request.setAttribute("telefonos", telefonos);
                        obtenerUltimoId = telefonoFacade.obtenerUltimoId();
                        request.setAttribute("ultimoId", obtenerUltimoId);
                        message = "Usuario encontrado";

                        request.setAttribute("successMessage", message);
                        request.setAttribute("persona", persona);

                    }

                } else {
                    id = 0;
                    message = "Usuario no encontrado";
                    request.setAttribute("Message", message);
                    System.out.println("Usuario no encontrado");
                }
            } else if ("add".equalsIgnoreCase(action)) {
                isCreate = true;
                if (isCreate && !telefono.isEmpty()) {
                    if (!telefono.matches("\\d+")) {
                        message = "El telefono solo debe contener numeros";

                        request.setAttribute("Message", message);
                    } else {
                        telefonoFacade.create(telefono2);

                        message = "Telefono agregado encontrado";

                        request.setAttribute("successMessage", message);
                    }

                } else {
                    message = "El telefono no puede ser vacío";

                    request.setAttribute("Message", message);
                }
            } else if ("edit".equalsIgnoreCase(action)) {
                Telefono telefonoExistente = telefonoFacade.searchTelefono(obtenerUltimoId);

                if (telefonoExistente != null) {
                    // Realizar las modificaciones necesarias en el objeto telefonoExistente
                    telefonoExistente.setNumero(selectedNumber);

                    // Actualizar el número de teléfono en la base de datos
                    telefonoFacade.edit(telefonoExistente);

                    message = "Telefono editado correctamente";

                    request.setAttribute("successMessage", message);
                }

            } else if ("remove".equalsIgnoreCase(action)) {
                Telefono telefonoExistente = telefonoFacade.searchTelefono(obtenerUltimoId);

                if (telefonoExistente != null) {
                    telefonoExistente.setNumero(selectedNumber);
                    telefonoFacade.remove(telefonoExistente);

                    message = "Telefono eliminado correctamente";

                    request.setAttribute("successMessage", message);
                }
            }
            request.getRequestDispatcher("telefonoControlador.jsp").forward(request, response);


}
catch (Exception e) {
    // Manejar la excepción aquí o imprimir información sobre la misma
    e.printStackTrace();
}

                   
        // Redirección aquí, dentro del bloque try

        } 




    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
