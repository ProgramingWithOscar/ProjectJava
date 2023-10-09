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
import uts.edu.java.modelo.Categoria;

/**
 *
 * @author cuent
 */
public class CategoriasControlador extends HttpServlet {

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
    private CategoriaFacade categoriaFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           String categoriaIdParam = request.getParameter("categoria_id");
int id;
            String name = request.getParameter("nombre_categoria");
            String action = request.getParameter("action");
            String message;
            
        if (categoriaIdParam != null && !categoriaIdParam.isEmpty()) {
            try {
                id = Integer.parseInt(categoriaIdParam);
            } catch (NumberFormatException e) {
                // Manejar el error de conversión aquí si es necesario
                e.printStackTrace();
                // Asignar un valor predeterminado o mostrar un mensaje de error apropiado
                id = 0; // Valor predeterminado en caso de error
            }
        } else {
            // El parámetro "categoria_id" no está presente en la solicitud
            // Puedes asignar un valor predeterminado o manejar este caso según sea necesario
            id = 0; // Valor predeterminado si el parámetro falta en la solicitud
        }






            
            Categoria categoria = new Categoria(id, name);
            
            if("add".equalsIgnoreCase(action)){
                categoria = categoriaFacade.buscaCategoria(id);
                if(categoria != null){
                    message = "El ID ya se encuentra registrado";
                    request.setAttribute("Message", message );  
                } else if(!name.isEmpty()) {
                    message = "Categoria agregada con exito";
                    request.setAttribute("successMessage", message );
                    Categoria categoria2 = new Categoria(id, name);
                    categoriaFacade.create(categoria2);
                }else {
                    message = "El nombre no puede ser vacío";
                    request.setAttribute("Message", message );
                }
                
            } else if("edit".equalsIgnoreCase(action)){
                categoria = categoriaFacade.buscaCategoria(id);
                if(categoria != null){
                    Categoria categoria2 = new Categoria(id, name);
                    categoriaFacade.edit(categoria2);
                    message = "Categoria editada con exito";
                    request.setAttribute("successMessage", message);
                }else {
                      message = "El ID ya se encuentra registrado";
                      request.setAttribute("Message", message );  
                }
            }else if("remove".equalsIgnoreCase(action)){
                categoria = categoriaFacade.buscaCategoria(id);
                if(categoria != null){
                    Categoria categoria2 = new Categoria(id, name);
                     message = "Categoria eliminada con exito";
                    request.setAttribute("successMessage", message);
                    categoriaFacade.remove(categoria2);
                } else {
                    message = "La categoria no existe";
                    request.setAttribute("Message", message);
                }
            }else if("search".equalsIgnoreCase(action)){
               categoria = categoriaFacade.buscaCategoria(id);
               if(categoria != null){
                   
                    Categoria categoria2 = new Categoria(id, name);
                    request.setAttribute("categoria", categoria);
                    message = "Categoria encontrada";
                    request.setAttribute("successMessage", message);
                    
                    
               }else {
                     message = "La categoria no existe";
                    request.setAttribute("Message", message);
               }
            }else if("Clean".equalsIgnoreCase(action)){
                Categoria categoria2 = new Categoria(id, name);
                id = 0;
                name = "";
                
                
                categoria2.setId(id);
                categoria2.setNombre(name);
            }
            
            request.setAttribute("todasCategorias", categoriaFacade.findAll());
            request.getRequestDispatcher("categoriasControlador.jsp").forward(request, response);
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
