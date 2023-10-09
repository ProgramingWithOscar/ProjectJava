<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <style>
            /* Estilo personalizado para centrar elementos verticalmente */
            .center-vertically {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh; /* Esto centra en la mitad de la pantalla verticalmente */
            }
        </style>
        <title>Telephones Administration</title>
    </head>
    <body class="bg-gray-200">
        <div>
            <a href="index.jsp" class="bg-green-300 p-3 m-10 rounded-md text-bold font-semibold">Regresar</a>
        </div>
        <div class="center-vertically">
            <form method="post" action="TelefonoControlador" class="bg-white p-8 rounded-md shadow-md space-y-4">
                <h1 class="text-center text-2xl font-semibold">Registro de n�meros telef�nicos</h1>
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
                    <label for="rut" class="w-1/4">C�dula :</label>
                    <input type="text" 
                           class="w-3/4 p-2 border rounded-md"
                           id="rut" 
                           name="rut" 
                           placeholder="Escriba la c�dula" 
                           required
                           value="${persona.rut}"
                    />
                    <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md" name="action" value="search">Buscar</button>
                </div>
                <div class="flex items-center space-x-4">
                    <label for="name" class="w-1/4">Nombre</label>
                    <input type="text" 
                           class="w-3/4 p-2 border rounded-md"
                           id="name" 
                           name="name" 
                           value="${persona.nombre}"
                           disabled
                    />
                </div>
                <div class="flex items-center space-x-4">
                    <label for="lastname" class="w-1/4">Apellidos</label>
                    <input type="text" 
                           class="w-3/4 p-2 border rounded-md"
                           id="lastname" 
                           name="lastname" 
                           value="${persona.apellido}"
                           disabled
                    />
                </div>
                <div class="flex items-center space-x-4">
                    <label for="name" class="w-1/4">Ultimo ID registrado</label>
                    <input type="text" 
                           class="w-3/4 p-2 border rounded-md"
                           id="id" 
                           name="id" 
                           value="${ultimoId}"
                           disabled
                    />
                </div>
                <div class="flex items-center space-x-4">
                    <label for="phone" class="w-1/4">Tel�fono</label>
                    <input type="text" 
                           maxlength="10"
                           class="w-3/4 p-2 border rounded-md"
                           id="phone" 
                           name="phone" 
                           placeholder="Escriba el tel�fono a agregar" 
                    />
                </div>
                <div class="flex items-center space-x-4">
                    <label for="rut" class="w-1/4">Tel�fonos agregados</label>
                    <select name="id_categoria" class="w-3/4 p-2 border rounded-md">
                        <c:if test="${empty  telefonos}">
                            <option>No tiene tel�fonos agregados</option>
                        </c:if>
                        <c:forEach items="${telefonos}" var="cate">
                            <option value="${cate.numero}" 
                                    ${persona.rut == cate.id ? 'selected' : ''}
                            >    
                                <c:out value="${cate.numero}"/>       
                            </option> 
                        </c:forEach>
                    </select>
                </div>
                <div class="flex items-center space-x-4">
                    <label for="phone" class="w-1/4" >Tel�fono seleccionado</label>
                    <input type="text" 
                           class="w-3/4 p-2 border rounded-md"
                           id="selectedPhone" 
                           name="selectedPhone" 
                           placeholder="Escriba el tel�fono a agregar" 
                           value="" 
                           
                    />
                </div>
                <div class="text-center">
                    <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-md" name="action" value="add">Agregar tel�fono</button>
                    <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md" name="action" value="edit">Editar tel�fono</button>
                    <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded-md" name="action" value="remove">Eliminar tel�fono</button>
                </div>
            </form>
        </div>
        <script>
            // Funci�n para actualizar el campo de entrada de texto con el n�mero seleccionado
            function actualizarTelefonoSeleccionado() {
                // Obtener el elemento select
                var select = document.querySelector('select[name="id_categoria"]');
                
                // Obtener el valor seleccionado
                var telefonoSeleccionado = select.value;
                
                // Actualizar el campo de entrada de texto
                var inputTelefono = document.querySelector('input#selectedPhone'); // Usamos el id
                inputTelefono.value = telefonoSeleccionado;
            }
            
            // Agregar un evento de cambio al select para llamar a la funci�n cuando cambie la selecci�n
            var select = document.querySelector('select[name="id_categoria"]');
            select.addEventListener('change', actualizarTelefonoSeleccionado);
            
            // Llamar a la funci�n inicialmente para establecer el valor inicial
            actualizarTelefonoSeleccionado();
        </script>
    </body>
</html>
