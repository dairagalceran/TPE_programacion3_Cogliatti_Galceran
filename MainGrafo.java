import java.util.Iterator;
import java.util.List;

public class MainGrafo {
    
    public static void main(String[] args) {

    /*
    *
    *   SETUP DE GRAFO
    *
    */

    // instanciamos grafo
    GrafoDirigido grafo = new GrafoDirigido<>();

    // carga de vertices
    grafo.agregarVertice(7);
    grafo.agregarVertice(1);
    grafo.agregarVertice(2);
    grafo.agregarVertice(3);
    grafo.agregarVertice(4);
    grafo.agregarVertice(6);
    grafo.agregarVertice(5);
    grafo.agregarVertice(8);


    grafo.agregarArco(3, 1, 10);
    grafo.agregarArco(4, 6, 15);
    grafo.agregarArco(8, 6, 20);
    grafo.agregarArco(8, 4, 25);
    grafo.agregarArco(8, 7, 30);
    grafo.agregarArco(1, 2, 35);
    grafo.agregarArco(1, 4, 40);
    grafo.agregarArco(5,3 , 45);
    grafo.agregarArco(7, 5, 50);
    grafo.agregarArco(2,3 , 55);
    grafo.agregarArco(6, 5, 60);
    grafo.agregarArco(4, 5, 65);
    grafo.agregarArco(1, 5, 70);
    grafo.agregarArco(2, 5, 75);



    /*
     *
     *   TESTEO DE METODOS DE GRAFO
     *
     */
    System.out.println("##############################################################");
    System.out.println("#                 TESTEO DE METODOS DE GRAFO                 #");
    System.out.println("##############################################################");


    // imprimir grafo
    System.out.println("Imprimir Grafo completo");
    System.out.println(grafo);

    // borrar vertice
    System.out.println("\n-------------------------------");
    System.out.println("Borrar vértice #2");
    grafo.borrarVertice(2);
    System.out.println(grafo);

    // agregar vertice
    System.out.println("\n-------------------------------");
    System.out.println("Agregar nuevamente el vertice 2\nAgregamos los arcos:\n      #2 -> #3\n      #2 -> #4\n      #1 -> #2\n      #2 -> #4");
    grafo.agregarVertice(2);
    grafo.agregarArco(1, 2, 35);
    grafo.agregarArco(2,3 , 55);
    grafo.agregarArco(2, 5, 75);

    // borrar arco
    System.out.println("\n-------------------------------");
    System.out.println("Borrar arco: #2 -> #4");
    grafo.borrarArco(2, 4);

    // re-imprimir grafo
    System.out.println("\n-------------------------------");
    System.out.println("Reimprimir el Grafo completo. Debe ser igual al del inicio");
    System.out.println(grafo);

    // verificar contiene vertice
    System.out.println("\n-------------------------------");
    System.out.println("Grafo contiene vertice #6 ? " + grafo.contieneVertice(6));
    System.out.println("Grafo contiene vertice #9 ? " + grafo.contieneVertice(9));

    // verificar contiene arco
    System.out.println("\n-------------------------------");
    System.out.println("Grafo contiene arco #7 -> #2 ? " + grafo.existeArco(7,2));
    System.out.println("Grafo contiene arco #1 -> #2 ? " + grafo.obtenerArco(1,2));

    // mostar arcos
    System.out.println("\n-------------------------------");
    System.out.println("Cantidad de arcos en el grafo: " + grafo.cantidadArcos());

    System.out.println("Los arcos del grafo son:");
    Iterator<Arco<Integer>> itA = grafo.obtenerArcos();
    int count = 0;
    while (itA.hasNext()) {
        count ++;
        Arco<Integer> a = itA.next();
        System.out.println(count +". " + a);
    }

    // motrar vertices
    System.out.println("\n-------------------------------");
    System.out.println("Cantidad de vertices en el grafo: "+ grafo.cantidadVertices());

    System.out.println("Los vertices del grafo son:");
    Iterator<Integer> iterador = grafo.obtenerVertices();
    while (iterador.hasNext()) {
        Integer v = iterador.next();
        System.out.print("#" + v + "  ");
    }
    System.out.println("");

    // obtener vertices adyacentes
    System.out.println("\n-------------------------------");
    int vertAdyacente= 4;
    System.out.println("Adyacentes del vértice #" + vertAdyacente);
    Iterator <Integer> iteratorVerticesAdyacentes = grafo.obtenerAdyacentes(vertAdyacente);
    while( iteratorVerticesAdyacentes.hasNext()){
        Integer vertice = iteratorVerticesAdyacentes.next();
        System.out.println("        -> #"+ vertice);
    }

    // obtener arcos de un vertice
    System.out.println("\n-------------------------------");
    System.out.println("Obtener arcos del vértice #1");
    Iterator<Arco<Integer>> iteratorArcos  = grafo.obtenerArcos(1);
    while(iteratorArcos.hasNext()){
        Arco<Integer> arco = iteratorArcos.next();
        System.out.println(arco);
    }

    System.out.println("\n");

    /*
     *
     *   TESTEO DE SERVICIOS
     *
     */

    System.out.println("##############################################################");
    System.out.println("#                    TESTEO DE SERVICIOS                     #");
    System.out.println("##############################################################");

    GrafoDirigido grafoBFS = new GrafoDirigido<>();

    grafoBFS.agregarVertice(7);
    grafoBFS.agregarVertice(1);
    grafoBFS.agregarVertice(2);
    grafoBFS.agregarVertice(3);
    grafoBFS.agregarVertice(4);
    grafoBFS.agregarVertice(6);
    grafoBFS.agregarVertice(5);

    grafoBFS.agregarArco(1, 3, null);
    grafoBFS.agregarArco(1, 2, null);
    grafoBFS.agregarArco(6, 4, null);
    grafoBFS.agregarArco(6, 7, null);
    grafoBFS.agregarArco(3, 5, null);
    grafoBFS.agregarArco(6, 1, null);
    grafoBFS.agregarArco(5, 6, null);
    grafoBFS.agregarArco(2,6, null);

    System.out.println("Nuevo Grafo: grafo2");
    System.out.println(grafoBFS);

    // test Servicio BFS
    System.out.println("-------------------------------");
    System.out.println("Servicio BFS en grafo2:");
    ServicioBFS bfs = new ServicioBFS(grafoBFS);
    System.out.println(bfs.bfsForest());

    System.out.println("BFS en grafo:");
    bfs = new ServicioBFS(grafo);
    System.out.println(bfs.bfsForest());

    // test Servicio DFS
    System.out.println("\n-------------------------------");
    System.out.println("Servicio DFS en grafo2:");
    ServicioDFS dfs = new ServicioDFS(grafoBFS);
    System.out.println(dfs.dfsForest());

    System.out.println("Servicio DFS en grafo:");
    dfs = new ServicioDFS(grafo);
    System.out.println(dfs.dfsForest());

    // test Servicio Caminos
    System.out.println("\n-------------------------------");
    System.out.println("Servicio Caminos en grafo:");
    ServicioCaminos servicioCaminos = new ServicioCaminos(grafo, 1, 3, 4);
    System.out.println("Caminos encontrados para grafo\n   Origen #1 -> destino #3\n   Limite de longitud 4\n   Caminos:");
        for (List<Integer> camino:  servicioCaminos.caminos()) {
            System.out.println("        1. "+camino);
        }


    }
}
