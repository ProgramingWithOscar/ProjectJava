<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.16/dist/tailwind.min.css" rel="stylesheet">
    <title>Formulario de Categoría</title>
</head>
<body class="bg-gray-200 flex flex-col items-center justify-center min-h-screen">
    <div class="m-10">
        <a href="index.jsp" class="bg-green-300 p-3 m-10 rounded-md text-bold font-semibold ">Regresar</a>
    </div>
    <div class="bg-white p-8 rounded shadow-md w-full md:w-80 mb-8 overflow-x-auto">
        <h1 class="text-2xl font-semibold mb-4 text-center text-blue-600">Gestión de Categorías</h1>
        <form method="POST" action="CategoriasControlador">
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
            <div class="mb-4">
                <label for="categoria_id" class="block text-gray-700 font-bold">ID de Categoría:</label>
                <input type="text" id="categoria_id" name="categoria_id" value="${categoria.id}" class="w-full border border-blue-400 rounded-md px-3 py-2 focus:ring-blue-500 focus:border-blue-500" required>
            </div>
            <div class="mb-4">
                <label for="nombre_categoria" class="block text-gray-700 font-bold">Nombre de la Categoría:</label>
                <input type="text" id="nombre_categoria" value="${categoria.nombre}" name="nombre_categoria" class="w-full border border-blue-400 rounded-md px-3 py-2 focus:ring-blue-500 focus:border-blue-500">
            </div>
            <div class="mb-4 flex justify-center">
                <button type="submit" name="action" value="add" class="bg-blue-500 text-white px-4 py-2 rounded-md mx-2 hover:bg-blue-600 focus:outline-none focus:ring focus:ring-blue-300">Agregar</button>
                <button type="submit" name="action" value="edit" class="bg-yellow-500 text-white px-4 py-2 rounded-md mx-2 hover:bg-yellow-600 focus:outline-none focus:ring focus:ring-yellow-300">Editar</button>
                <button type="submit" name="action" value="remove" class="bg-red-500 text-white px-4 py-2 rounded-md mx-2 hover:bg-red-600 focus:outline-none focus:ring focus:ring-red-300">Eliminar</button>
            </div>
            <div class="mb-4 flex justify-center">
                <button type="submit" name="action" value="search" class="bg-green-500 mr-2 text-white px-4 py-2 rounded-md hover:bg-green-600 focus:outline-none focus:ring focus:ring-green-300">Buscar por ID</button>
            </div>
        </form>
    </div>
    <div class="overflow-x-auto w-full flex items-center">
        <table class="w-sm bg-white border-collapse border border-gray-300 mx-auto mb-10">
            <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-600 uppercase tracking-wider">Id categoría</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-600 uppercase tracking-wider">Nombre categoría</th>
                </tr>
            </thead>
            <tbody class="divide-y divide-gray-200">
                <c:forEach items="${todasCategorias}" var="cate" begin="0">
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap">${cate.id}</td>
                        <td class="px-6 py-4 whitespace-nowrap">${cate.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
