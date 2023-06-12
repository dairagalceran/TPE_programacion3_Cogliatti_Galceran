import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioBFS {

	private Grafo<?> grafo;
    private HashMap<Integer, Boolean>  listaVerticesVisitados;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
        this.listaVerticesVisitados = new HashMap<>();
	}
	
    public Iterator<Integer> inicializarVerticesFalse(){
		Iterator<Integer> iteratorVertices = this.grafo.obtenerVertices();
		while(iteratorVertices.hasNext()){
			int vertice = iteratorVertices.next();
			listaVerticesVisitados.put(vertice, false);
        }
		return this.listaVerticesVisitados.keySet().iterator();
	}


	public List<Integer> bfsForest() {
        Iterator<Integer> iteratorVerticesVisitados = this.inicializarVerticesFalse();
        List<Integer> caminoVerticesBFS = new ArrayList<>();
        
        while(iteratorVerticesVisitados.hasNext()){
            Integer verticeVisitadoId = iteratorVerticesVisitados.next();
        
            if(listaVerticesVisitados.get(verticeVisitadoId) == false){
                caminoVerticesBFS.addAll(this.bfsForest(verticeVisitadoId));
            }
        }
		return caminoVerticesBFS;
	}

	
	private List<Integer> bfsForest(int verticeId) {
        
        List<Integer> caminoVertices = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        
        this.listaVerticesVisitados.put(verticeId, true);
        row.add(verticeId);

        while(! row.isEmpty()){

            Integer removedVertice =  row.remove(0);
            caminoVertices.add(removedVertice);

    
            Iterator<Integer> iteratorVerticesAdyacentes = this.grafo.obtenerAdyacentes(removedVertice);
            while(iteratorVerticesAdyacentes.hasNext()){
                Integer verticeAdyacente = iteratorVerticesAdyacentes.next();

                if(listaVerticesVisitados.get(verticeAdyacente) == false){
                    this.listaVerticesVisitados.put(verticeAdyacente, true);
                    row.add(verticeAdyacente);   
                }
            }
        }
        return caminoVertices;
    }

    
}