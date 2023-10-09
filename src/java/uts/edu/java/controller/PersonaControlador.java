/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uts.edu.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.edu.java.ejb.CategoriaFacade;
import uts.edu.java.ejb.PersonaFacade;
import uts.edu.java.modelo.Categoria;
import uts.edu.java.modelo.Persona;

/**
 *
 * @author cuent
 */
public class PersonaControlador extends HttpServlet {

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
    private CategoriaFacade categoriaFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Escribir codigo para enlazar  (formulario, modelo, servicio)
            
            // Entrada >> captura de datos
            
            String action = request.getParameter("action");
            String rut = request.getParameter("rut");
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            int id_categoria = 0;
            String Message;
            Categoria categoria = new Categoria();
            if(request.getParameter("id_categoria") != null){
                id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
                categoria = categoriaFacade.buscaCategoria(id_categoria);
            }
            boolean isCreate = false;
            
            // Instanciar un objeto persona
            
            Persona persona = new Persona(rut, name, lastname, categoria);
          
            // Proceso >> según el valor del action se realiza cosas diferentes
            
            if("Create".equalsIgnoreCase(action)){     
                isCreate = true;
                if(isCreate) {
                    
                    if(personaFacade.searchPersona(persona.getRut()) != null){
                        Message = "El numero de RUT ya está registrado";
                        request.setAttribute("Message", Message);
                        
                    } else {
                    personaFacade.create(persona);
                    Message = "Cliente registrado con exito";
                    request.setAttribute("successMessage", Message);
                    System.out.println("Facade " + personaFacade.searchPersona(rut).toString());
                    System.out.println("Persona" + persona.getRut()); 
                    }
                   
                }
                
              
            }else if("Edit".equalsIgnoreCase(action)){
                if(personaFacade.searchPersona(rut) != null){
                Message = "Cliente editado con exito";
                request.setAttribute("successMessage", Message);
                personaFacade.edit(persona);
                } else{
                Message = "Cliente no encontrado";
                request.setAttribute("Message", Message);
                }
               
            }else if("Remove".equalsIgnoreCase(action)){
                Message = "Cliente eliminado correctamente";
                request.setAttribute("Message", Message);
                personaFacade.remove(persona);
            }else if("Search".equalsIgnoreCase(action)){
                persona = personaFacade.searchPersona(rut);
                if(persona != null){
                 id_categoria = persona.getIdCategoria().getId();
                 categoria = categoriaFacade.buscaCategoria(id_categoria);
                 Message = "Cliente encontrado con exito";
                 request.setAttribute("successMessage", Message);
                }else {
                  Message = "El cliente no fue encontrado";
                  request.setAttribute("Message", Message);  
                }
            }else if("Clean".equalsIgnoreCase(action)){
                rut = "";
                name = "";
                lastname = "";
                id_categoria = 0;
                persona.setRut(rut);
                persona.setNombre(name);
                persona.setApellido(lastname);
                persona.setIdCategoria(categoria);
            }
            
            request.setAttribute("categoria", categoria );
            request.setAttribute("persona",  persona);
            request.setAttribute("todasCategorias", categoriaFacade.findAll());
            request.setAttribute("todasPersonas", personaFacade.findAll());
            
            request.getRequestDispatcher("personaInfo.jsp").forward(request, response);
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
