<%-- 
    Document   : personaInfo
    Created on : 3 oct. 2023, 8:37:37
    Author     : cuent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Customer Administration</title>
</head>
<body class="bg-gray-100">
    <div>
        <a href="index.jsp" class="bg-green-300 p-3 m-10 rounded-md text-bold font-semibold">Regresar</a>
    </div>

    <div class="container mx-auto p-4">
        <h1 class="text-3xl font-semibold mb-4">Administrar Clientes</h1>
        <form method="POST" action="PersonaControlador" class="space-y-4">
            <c:if test="${not empty Message}">
                <div class="bg-red-100 text-red-600 border border-red-400 px-4 py-2 rounded-md mb-4">
                    ${Message}
                </div>
            </c:if>
            <c:if test="${not empty successMessage}">
                <div class="bg-green-100 text-white-600 border border-green-400 px-4 py-2 rounded-md mb-4">
                    ${successMessage}
                </div>
            </c:if>
            <div class="flex items-center space-x-4">
                <label for="rut" class="w-1/4">Cédula :</label>
                <input type="text" 
                       class="w-3/4 p-2 border rounded-md"
                       id="rut" 
                       name="rut" 
                       placeholder="Escriba la cédula" 
                       required 
                       value="${persona.rut}"
                />
            </div>
                
            <div class="flex items-center space-x-4">
                <label for="name" class="w-1/4">Nombre :</label>
                <input type="text" 
                       class="w-3/4 p-2 border rounded-md"
                       id="name" 
                       name="name" 
                       placeholder="Escriba el nombre" 
                       value="${persona.nombre}"
                />
            </div>
            <div class="flex items-center space-x-4">
                <label for="name" class="w-1/4">Apellidos: :</label>
                <input type="text" 
                       class="w-3/4 p-2 border rounded-md"
                       id="lastname" 
                       name="lastname" 
                       placeholder="Escriba los apellidos" 
                       value="${persona.apellido}"
                />
            </div>
            <div class="flex items-center space-x-4">
                <label for="rut" class="w-1/4">Categoría: </label>
                <select name="id_categoria" class="w-3/4 p-2 border rounded-md">
                    <c:if test="${empty todasCategorias}">
                        <option>Por favor seleccione...</option>
                    </c:if>
                    <c:forEach items="${todasCategorias}" var="cate">
                        <option value="${cate.id}" 
                                ${categoria.id == cate.id ? 'selected' : ''}>    
                                <c:out value="${cate.nombre}"/>       
                        </option> 
                    </c:forEach>
                </select>
            </div>
            <div class="mt-4 space-x-4 text-center">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md" name="action" value="Create">Create</button>
                <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-md" name="action" value="Edit">Edit</button>
                <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded-md" name="action" value="Remove" onclick="javascript:if(!confirm('¿Esta seguro de eliminar?')) return false">Remove</button>
                <button type="submit" class="bg-blue-400 text-white px-4 py-2 rounded-md" name="action" value="Search">Search</button>
                <button type="submit" class="bg-yellow-500 text-white px-4 py-2 rounded-md" name="action" value="Clean">Clean</button>
            </div>  
        </form>
        <br><br>
        <table class="w-full bg-white shadow-md rounded-md divide-y divide-gray-300">
            <thead class="bg-gray-200">
                <tr>
                    <th class="p-3 text-left border-b-2">Rut</th>
                    <th class="p-3 text-left border-b-2">Name</th>
                    <th class="p-3 text-left border-b-2">Lastname</th>
                    <th class="p-3 text-left border-b-2">Category</th>
                    <!-- Agrega más encabezados de tabla si es necesario -->
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todasPersonas}" var="pers" begin="0">
                    <tr>
                        <td class="p-3 border-b">${pers.rut}</td>
                        <td class="p-3 border-b">${pers.nombre}</td>
                        <td class="p-3 border-b">${pers.apellido}</td>
                        <td class="p-3 border-b">${pers.idCategoria.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </div>
</body>
</html>
