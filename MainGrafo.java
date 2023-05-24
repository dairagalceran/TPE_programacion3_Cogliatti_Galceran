


public class MainGrafo {
    
    public static void main(String[] args) {
        

    GrafoDirigido grafo = new GrafoDirigido<>();

    grafo.agregarVertice(7);
    grafo.agregarVertice(1);
    grafo.agregarVertice(2);
    grafo.agregarVertice(3);
    grafo.agregarVertice(4);
    grafo.agregarVertice(6);
    grafo.agregarVertice(5);
    grafo.agregarVertice(9);
    

    grafo.agregarArco(3, 1, null);
    grafo.agregarArco(4, 6, null);
    grafo.agregarArco(8, 6, null);
    grafo.agregarArco(8, 4, null);
    grafo.agregarArco(8, 7, null);
    grafo.agregarArco(1, 2, null);
    grafo.agregarArco(1, 4, null);
    grafo.agregarArco(5,3 , null);
    grafo.agregarArco(7, 5, null);
    grafo.agregarArco(2,3 , null);
    grafo.agregarArco(6, 5, null);
    grafo.agregarArco(4, 5, null);
    grafo.agregarArco(1, 5, null);
    grafo.agregarArco(2, 5, null);

    /* 
    System.out.println(" Grafo ");
    grafo.imprimirGrafo();


    System.out.println("borrar  v:2");
    grafo.borrarVertice(2);
    grafo.imprimirGrafo();

    
    System.out.println("delete  v:2");
    grafo.deleteVertice(2);
    grafo.imprimirGrafo();
    
    System.out.println("agregar arco: 2,4; 7,2 ; 3,2");
    grafo.agregarArco(2, 4, null);
    grafo.agregarArco(7, 2, null);
    grafo.agregarArco(3, 2, null);
    grafo.imprimirGrafo();

    System.out.println(" borrar arco: 1,1");
    grafo.borrarArco(1, 1);
    grafo.imprimirGrafo();

    System.out.println(" contiene v:6");
    System.out.println(grafo.contieneVertice(6));

    System.out.println(" contiene v:3");
    System.out.println(grafo.contieneVertice(3));

    System.out.println(" contiene arco:7,2");
    System.out.println(grafo.existeArco(7,2));
    

    System.out.println(" contiene arco:7,2");
    System.out.println(grafo.obtenerArco(7,2));

    System.out.println(" cantidad vertices");
    System.out.println(grafo.cantidadVertices());

    System.out.println(" cantidad arcos");
    System.out.println(grafo.cantidadArcos());

    
    System.out.println(" obtener vertices");
    Iterator<Integer> iteratorVertices = grafo.obtenerVertices(); 
    System.out.print("Vértices de la matriz de adyacencia: ");
    while(iteratorVertices.hasNext()){
        Integer vertice = iteratorVertices.next();
        System.out.print(" " +vertice + ", ");
    };

    System.out.println();

    System.out.println(" obtener arcos adyacentes de  v:1");
    int vertAdyacente= 4;
    Iterator <Integer> iteratorVerticesAdyacentes = grafo.obtenerAdyacentes(vertAdyacente);
    while( iteratorVerticesAdyacentes.hasNext()){
        Integer vertice = iteratorVerticesAdyacentes.next();
        System.out.println("Adyacentes del vertice " + vertAdyacente+" : "+ vertice);
    }
    

    System.out.println("Obtener  arcos del v:1");
    Iterator<Arco<Integer>> iteratorArcos  = grafo.obtenerArcos(vertAdyacente);
    while(iteratorArcos.hasNext()){
        Arco<Integer> arco = iteratorArcos.next();
        System.out.println(arco +  " , ");
    }

    System.out.println("Obtener  arcos ");
    iteratorArcos  = grafo.obtenerArcos();
    while(iteratorArcos.hasNext()){
        Arco<Integer> arco = iteratorArcos.next();
        System.out.println(arco +  " , ");
    }
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
    grafo.imprimirGrafo();

    ServicioBFS bfs = new ServicioBFS(grafoBFS);

    System.out.println("BFS");
    System.out.println(bfs.bfsForest());

    
    ServicioDFS dfs = new ServicioDFS(grafoBFS);

    System.out.println("DFS del grafo BFS");
    System.out.println(dfs.dfsForest());


    System.out.println(" contiene v:8");
    System.out.println(grafo.contieneVertice(8));


    ServicioCaminos caminos = new ServicioCaminos(grafo, 1, 3, 3);
    System.out.println("-----------CAMINOS-------------");
    System.out.println("Main: " +caminos.caminos());
    }
}
