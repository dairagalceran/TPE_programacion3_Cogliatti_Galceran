import java.util.Iterator;

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



    // imprimir grafo
    System.out.println("-------------------------------");
    System.out.println(" Grafo ");
    System.out.println(grafo);

    // borrar vertice
    System.out.println("-------------------------------");
    System.out.println("borrar vértice #2");
    grafo.borrarVertice(2);
    System.out.println(grafo);

    // agregar vertice
    System.out.println("-------------------------------");
    System.out.println("Agregar arcos:\n      #2 -> #3\n      #2 -> #4\n      #1 -> #2\n      #2 -> #4\"");
    grafo.agregarVertice(2);
    grafo.agregarArco(1, 2, 35);
    grafo.agregarArco(2,3 , 55);
    grafo.agregarArco(2, 5, 75);
    System.out.println(grafo);

    // borrar arco
    System.out.println("-------------------------------");
    System.out.println(" borrar arco: #2 -> #4");
    grafo.borrarArco(2, 4);
    System.out.println(grafo);

    // verificar contiene vertice
    System.out.println("-------------------------------");
    System.out.println("Grafo contiene vertice #6 ?");
    System.out.println(grafo.contieneVertice(6));
    System.out.println("Grafo contiene vertice #9 ?");
    System.out.println(grafo.contieneVertice(9));

    // verificar contiene arco
    System.out.println("-------------------------------");
    System.out.println("Grafo contiene arco #7 -> #2 ?");
    System.out.println(grafo.existeArco(7,2));
    System.out.println("Grafo contiene arco #1 -> #2 ?");
    System.out.println(grafo.obtenerArco(1,2));

    // mostar arcos
    System.out.println("-------------------------------");
    System.out.println("Cantidad de arcos en el grafo: " + grafo.cantidadArcos());

    System.out.println("Los arcos del grafo son:");
    Iterator<Arco<Integer>> itA = grafo.obtenerArcos();
    while (itA.hasNext()) {
        Arco<Integer> a = itA.next();
        System.out.println(a + "  ");
    }

    // motrar vertices
    System.out.println("-------------------------------");
    System.out.println("Cantidad de vertices en el grafo: "+ grafo.cantidadVertices());

    System.out.println("Los vertices del grafo son:");
    Iterator<Integer> iterador = grafo.obtenerVertices();
    while (iterador.hasNext()) {
        Integer v = iterador.next();
        System.out.print(v + "  ");
    }
    System.out.println("");

    // obtener vertices adyacentes
    System.out.println("-------------------------------");
    System.out.println("Adyacentes del vértice #4");
    int vertAdyacente= 4;
    Iterator <Integer> iteratorVerticesAdyacentes = grafo.obtenerAdyacentes(vertAdyacente);
    while( iteratorVerticesAdyacentes.hasNext()){
        Integer vertice = iteratorVerticesAdyacentes.next();
        System.out.println("        -> #"+ vertice);
    }

    // obtener arcos de un vertice
    System.out.println("-------------------------------");
    System.out.println("Obtener arcos del vértice #1");
    Iterator<Arco<Integer>> iteratorArcos  = grafo.obtenerArcos(vertAdyacente);
    while(iteratorArcos.hasNext()){
        Arco<Integer> arco = iteratorArcos.next();
        System.out.println(arco +  " , ");
    }



    /*
     *
     *   TESTEO DE SERVICIOS
     *
     */



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
   
    System.out.println("------grafo----------");
    System.out.println(grafo);

    // test Servicio BFS
    ServicioBFS bfs = new ServicioBFS(grafoBFS);
    System.out.println("BFS en grafo BFS");
    System.out.println(bfs.bfsForest());

    bfs = new ServicioBFS(grafo);
    System.out.println("BFS en grafo");
    System.out.println(bfs.bfsForest());

    // test Servicio DFS
    ServicioDFS dfs = new ServicioDFS(grafoBFS);
    System.out.println("DFS del grafoBFS");
    System.out.println(dfs.dfsForest());

    int verticeBuscado = 8;
    System.out.println(" contiene vertice :"+verticeBuscado);
    System.out.println(grafo.contieneVertice(verticeBuscado));

    // test Servicio Caminos
    ServicioCaminos caminos = new ServicioCaminos(grafo, 1, 3, 4);
    System.out.println("-----------CAMINOS-------------");
    System.out.println("Main: " +caminos.caminos());


    }
}
