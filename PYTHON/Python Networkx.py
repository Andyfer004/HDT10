import networkx as nx

# Crear grafo
grafo = nx.Graph()

# Leer archivo y agregar distancias al grafo
def leer_archivo(file_path):
    with open(file_path, 'r') as file:
        for line in file:
            ciudad1, ciudad2, distancia = line.strip().split(',')
            grafo.add_edge(ciudad1, ciudad2, distancia=int(distancia))

# Calcular ruta más corta entre dos universidades
def ruta_corta(universidad_actual, universidad_final, clima_viaje):
    # Definir función de peso de las aristas según el clima
    def peso_arista(u, v, data):
        distancia = data['distancia']
        clima = clima_viaje
        if clima == 2:  # Lluvioso
            return distancia * 1.1
        elif clima == 3:  # Nevado
            return distancia * 1.5
        elif clima == 4:  # Ventoso
            return distancia * 1.3
        else:  # Normal
            return distancia

    # Calcular ruta más corta utilizando el algoritmo de Dijkstra
    try:
        ruta = nx.shortest_path(grafo, universidad_actual, universidad_final, weight=peso_arista)
        distancia_total = nx.shortest_path_length(grafo, universidad_actual, universidad_final, weight=peso_arista)
        return f'Ruta: {" -> ".join(ruta)}\nDistancia total: {distancia_total}'
    except nx.NetworkXNoPath:
        return 'No existe ruta entre las universidades especificadas.'

# Calcular centro del grafo
def calcular_centro_grafo():
    centro = nx.center(grafo)
    return centro[0] if centro else 'El grafo no tiene un centro definido.'

# Modificar grafo: Interrupción de tráfico entre ciudades
def interrupcion_trafico(ciudad1, ciudad2):
    if grafo.has_edge(ciudad1, ciudad2):
        grafo.remove_edge(ciudad1, ciudad2)
        print(f'Interrupción de tráfico entre {ciudad1} y {ciudad2} realizada.')
    else:
        print(f'No existe una conexión entre {ciudad1} y {ciudad2}.')

# Modificar grafo: Establecer conexión entre ciudades
def establecer_conexion(ciudad1, ciudad2, tiempo):
    if grafo.has_edge(ciudad1, ciudad2):
        print(f'Ya existe una conexión entre {ciudad1} y {ciudad2}.')
    else:
        grafo.add_edge(ciudad1, ciudad2, distancia=tiempo)
        print(f'Conexión establecida entre {ciudad1} y {ciudad2} con tiempo {tiempo}.')

# Modificar grafo: Indicar clima entre ciudades
def indicar_clima(ciudad1, ciudad2, clima):
    if grafo.has_edge(ciudad1, ciudad2):
        grafo[ciudad1][ciudad2]['clima'] = clima
        print(f'Clima entre {ciudad1} y {ciudad2} modificado.')
    else:
        print(f'No existe una conexión entre {ciudad1} y {ciudad2}.')

# Archivo de distancias
file_path = 'distancias.txt'
leer_archivo(file_path)

print('-----------------------------------------')
print('BIENVENIDO AL PROGRAMA DE RUTAS UNIVERSITARIAS')
print('-----------------------------------------')

opcion = -1
while opcion != 0:
    print('1. Ruta más corta entre dos universidades')
    print('2. Nombre de la Universidad en el centro del grafo')
    print('3. Modificar grafo')
    print('4. Acerca del programa')
    print('0. Salir')
    opcion = int(input('Ingrese una opción: '))

    if opcion == 1:
        print('¿En qué clima piensa viajar?')
        print('1. Normal')
        print('2. Lluvioso')
        print('3. Nevado')
        print('4. Ventoso')
        clima_viaje = int(input('Ingrese el número correspondiente al clima: '))

        universidad_actual = input('Ingrese la Universidad donde se encuentra: ')
        universidad_final = input('Ingrese la Universidad hacia donde se dirige: ')
        resultado = ruta_corta(universidad_actual, universidad_final, clima_viaje)
        print(resultado)

    elif opcion == 2:
        centro_grafo = calcular_centro_grafo()
        print('Ciudad del centro del grafo:', centro_grafo)

    elif opcion == 3:
        print('Modificación de Grafo')
        print('1. Interrupción de tráfico entre ciudades')
        print('2. Establecer conexión entre ciudades')
        print('3. Indicar clima entre ciudades')
        opcion_modificacion = int(input('Ingrese una opción: '))

        if opcion_modificacion == 1:
            ciudad1 = input('Ingrese la primera ciudad: ')
            ciudad2 = input('Ingrese la segunda ciudad: ')
            interrupcion_trafico(ciudad1, ciudad2)

        elif opcion_modificacion == 2:
            ciudad1 = input('Ingrese la primera ciudad: ')
            ciudad2 = input('Ingrese la segunda ciudad: ')
            tiempo = int(input('Ingrese el tiempo de viaje entre las ciudades: '))
            establecer_conexion(ciudad1, ciudad2, tiempo)

        elif opcion_modificacion == 3:
            ciudad1 = input('Ingrese la primera ciudad: ')
            ciudad2 = input('Ingrese la segunda ciudad: ')
            clima = int(input('1. Normal\n2. Lluvia\n3. Nieve\n4. Tormenta\nIngrese el clima: '))
            indicar_clima(ciudad1, ciudad2, clima)

        else:
            print('Opción no válida')

        centro_grafo = calcular_centro_grafo()
        print('Nuevo centro del grafo:', centro_grafo)

    elif opcion == 4:
        print('Acerca del programa')
        print('Este programa fue desarrollado por Gabriel Alberto Paz González y Andy Fernando Fuentes Velásquez')
        print('para la clase de Algoritmos y Estructuras de Datos de la Universidad del Valle de Guatemala')
        print()
        print('El programa consiste en un grafo que contiene las distancias entre las universidades')
        print('y calcula la ruta más corta entre dos universidades, tomando en cuenta')
        print('el clima en el que se viaja')

    elif opcion == 0:
        print('¡Hasta luego!')

    else:
        print('Opción no válida')
