<!DOCTYPE html>
<html>

<head>
    <title>Administration</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body class="bg-white">
    <div class="container mx-auto p-4 text-black">
        <nav class="bg-gray-100 p-4 rounded-lg">
            <div class="container mx-auto flex justify-between items-center">
                <ul class="flex space-x-4 text-black">
                    <li class="nav-item active bg-green-400 p-2 rounded-lg">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item bg-green-400 p-2 rounded-lg">
                        <a class="nav-link" href="PersonaControlador">Administrar Clientes</a>
                    </li>
                    <li class="nav-item bg-green-400 p-2 rounded-lg">
                        <a class="nav-link" href="telefonoControlador.jsp">Administrar telefonos</a>
                    </li>
                    <li class="nav-item bg-green-400 p-2 rounded-lg">
                        <a class="nav-link" href="CategoriasControlador">Administrar categorias</a>
                    </li>
                </ul>
                <form class="flex">
                    <input type="search" class="p-2 bg-gray-200 text-black rounded-md" placeholder="Búsqueda">
                    <button type="submit" class="ml-2 p-2 bg-green-600 rounded-md text-white">
                        <span class="glyphicon glyphicon-eye-open"></span> Buscar
                    </button>
                </form>
            </div>
        </nav>
        <div class="bg-gradient-to-b from-green-600 via-green-400 to-gray-200 p-8 rounded-lg mt-4 border-2">
            <div class="grid grid-cols-1 md:grid-cols-2">
                <div class="md:col-span-1 flex items-center">
                    <img src="images/app-uts.png" alt="Logo UTS" class="w-40" />
                </div>
                <div class="md:col-span-1">
                    <h2 class="text-3xl font-semibold">Programación Java WEB</h2>
                    <h2 class="text-3xl font-semibold">Grupo: B191. Persistencia. JPQL</h2>
                </div>
            </div>
        </div>

        <header class="mt-8">
            <div class="col-span-1 text-center">
                <h3 class="text-2xl">Unidades Tecnológicas de Santander.</h3>
                <h3 class="text-2xl">Estudiante: Oscar Eduardo Poveda Lozada</h3>
            </div>
        </header>
        <footer class="mt-8 text-center">
            <p>&copy; - Derechos de autor: Oscar Eduardo Poveda Lozada - oscarpoveda679@gmail.com</p>
        </footer>
    </div>

</body>

</html>
