import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

    private Grafo<?> grafo;
	private HashMap<Integer,String> verticesColor;
	

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.verticesColor = new HashMap<Integer, String>();

	}

	
	public Iterator<Integer> inicializarVerticesColor(){
		Iterator<Integer> iteratorVertices = this.grafo.obtenerVertices();
		while(iteratorVertices.hasNext()){
			int vertice = iteratorVertices.next();
			verticesColor.put(vertice, "white");
		}
																//System.out.println(this.verticesColor.keySet());
		return this.verticesColor.keySet().iterator();
	}
	
	
	public List<Integer> dfsForest() {
		List<Integer> caminoVertices = new ArrayList<>();
		Iterator<Integer> iteratorVerticesColor= this.inicializarVerticesColor();

		while(iteratorVerticesColor.hasNext()){
			Integer verticeId = iteratorVerticesColor.next();	

			if(verticesColor.get(verticeId).equals("white")){
				caminoVertices.addAll(this.dfsForest(verticeId));
			}					
		}
		return caminoVertices;
	}
	

	private List<Integer> dfsForest(int verticeId){
		List<Integer> caminoVerticesAdyacentes = new ArrayList<>();
		Iterator<Integer> iteratorVerticesAdyacentes = this.grafo.obtenerAdyacentes(verticeId);

		if(verticesColor.get(verticeId).equals("white")){
			verticesColor.put(verticeId, "yellow");	
			caminoVerticesAdyacentes.add(verticeId);
		}	

		while(iteratorVerticesAdyacentes.hasNext()){
			Integer verticeProximo = iteratorVerticesAdyacentes.next();

			String verticeColor =  this.verticesColor.get(verticeProximo);

			if( verticeColor.equals("white")){
				verticesColor.replace(verticeProximo, verticeColor); // esta linea esta de mas
				caminoVerticesAdyacentes.addAll(this.dfsForest(verticeProximo));
			}
		}
		verticesColor.replace(verticeId, "black"); // me parece que faltaba esta linea
		return caminoVerticesAdyacentes;
	}

}
