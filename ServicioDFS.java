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
																				//System.out.println("verticeId "+ verticeId+ " -> color " + verticesColor.get(verticeId));
			if(verticesColor.get(verticeId).equals("white")){
				caminoVertices.addAll(this.dfsForest(verticeId));
			}					
		}
																				//System.out.println("return def "+ caminoVertices);
		return caminoVertices;
	}
	

	private List<Integer> dfsForest(int verticeId){
		List<Integer> caminoVerticesAdyacentes = new ArrayList<>();
		Iterator<Integer> iteratorVerticesAdyacentes = this.grafo.obtenerAdyacentes(verticeId);																	//System.out.println(verticesColor.get(verticeId).equals("yellow"));

		if(verticesColor.get(verticeId).equals("white")){
			verticesColor.put(verticeId, "yellow");	
			caminoVerticesAdyacentes.add(verticeId);
		}	

		while(iteratorVerticesAdyacentes.hasNext()){
			Integer verticeProximo = iteratorVerticesAdyacentes.next();
																				//System.out.println(verticesColor.get(verticeProximo).equals("yellow"));
			String verticeColor =  this.verticesColor.get(verticeProximo);
																				//System.out.println("verticeproximo "+ verticeProximo+ " -> color " + verticesColor.get(verticeProximo));
			if( verticeColor.equals("white")){
				verticesColor.replace(verticeProximo, verticeColor);
				caminoVerticesAdyacentes.addAll(this.dfsForest(verticeProximo));
			}
		}
																			//	System.out.println("return dfs con VERTICE "+ caminoVerticesAdyacentes);
		return caminoVerticesAdyacentes;
	}

}
