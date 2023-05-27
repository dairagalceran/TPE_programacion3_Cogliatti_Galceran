import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap< Integer , ArrayList<Arco<T>>> matrizAdyacencia;

	public GrafoDirigido(){
		this.matrizAdyacencia = new HashMap< Integer , ArrayList<Arco<T>>>();	
	}



	@Override
	public void agregarVertice(int lastVerticeId) {
		if(!this.contieneVertice(lastVerticeId)){
			this.matrizAdyacencia.put(lastVerticeId, new ArrayList<Arco<T>>());
		}
	}


	@Override
	public void borrarVertice(int verticeId) {
		Iterator<Integer> verticeIdIterator = this.matrizAdyacencia.keySet().iterator(); // O(1)
		while(verticeIdIterator.hasNext()){   // // O(V) puede ser que en el peor de los casas  deba recorrer todos los vertices para encontrar en que busca borrar
			Integer vertice = verticeIdIterator.next();  // O(1)
			if(vertice != verticeId){
				ArrayList<Arco<T>> arcosQueNoApuntanAlVerticeBorrado = new ArrayList<Arco<T>>();
				ArrayList<Arco<T>> arcos = this.matrizAdyacencia.get(vertice); // O(1)
				Iterator<Arco<T>> arcosIterator = arcos.iterator(); // O(1)
				while(arcosIterator.hasNext()){   // O(A) siendo A arcos que apuntan al vertice borrado
					Arco<T> arco = arcosIterator.next(); // O(1)
					if(arco.getVerticeDestino() != verticeId){
						arcosQueNoApuntanAlVerticeBorrado.add(arco); // O(1)
					}
				}
				this.matrizAdyacencia.replace(vertice, arcosQueNoApuntanAlVerticeBorrado); // O(1)
			}
		}
		this.matrizAdyacencia.remove(verticeId); // O(1)
	}
	
	/**
      * Complejidad: O(2a) = O(a) debido a que deben verificar si existe  el Arco
      * "buscar el tamaño de las claves del hashMap  de la matrizAdyacencia
      */

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.contieneVertice(verticeId1) & this.contieneVertice(verticeId2)){
			Arco<T> arco = new Arco<T>(verticeId1,verticeId2,etiqueta);
			this.matrizAdyacencia.get(verticeId1).add(arco);
		}
	}

	//????????????

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		Arco<T> arco = this.obtenerArco(verticeId1,verticeId2);
		if( arco !=null )
			this.matrizAdyacencia.get(verticeId1).remove(arco);
	}

	/**
      * Complejidad: O(1)  debido a que debe
      * verifica  si el vertice  es una de las clave  del hashMap  con la función containsKey()
      */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.matrizAdyacencia.containsKey(verticeId);
	}

	/**
      * Complejidad: O(1)  debido a que debe
      * "obtiene la respuesta llamando a otra función
      */

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return this.obtenerArco(verticeId1, verticeId2) != null;
	}

	/**
      * Complejidad: O(a) donde a es la cantidad maxima de arcos salientes desde el vertice origen  dado
	  * debido a que debe  "buscar el vertice saliente O(1) y luego recorrer el ArrayList de arcos salientes 
	  *  para verificar si existe un arco con destino igual al dado por parámetro, siendo
	  *en el peor de los casos O(h), h = cantidad de arcos salientes del vertice pasado 
	  *
      */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// check existen this.vertices
		if(!contieneVertice(verticeId1) & !contieneVertice(verticeId2))
			return null;
		for (Arco<T> arco: this.matrizAdyacencia.get(verticeId1)) {
			if (arco.getVerticeDestino() == verticeId2)
				return arco;
		}
		return null;
	}

	/**
      * Complejidad: O(1)  debido a que debe
      * "buscar el tamaño de las claves del hashMap  de la matrizAdyacencia
      */

	@Override
	public int cantidadVertices() {
		return this.matrizAdyacencia.keySet().size();
	}

	/**
      * Complejidad: O(V)  debido a que debe
      * buscar por cada vertice la cantidad total de arcos que salen desde ese vertice yEEEEEEE
      */

	@Override
	public int cantidadArcos() {
		int count = 0;
		for( ArrayList<Arco<T>> arcos: this.matrizAdyacencia.values()){
			count += arcos.size();
		}
		return count;
	}
		
	/**
      * Complejidad: O(1)  debido a que  se utiliza la 
      * función  keySet() de HashMap devuelve un iterator  sobre los vertices  o  claves obtenidos por keySet()
      */
	@Override
	public Iterator<Integer> obtenerVertices() {
		Iterator<Integer> iteratorDeVertices = this.matrizAdyacencia.keySet().iterator(); // O(1)
		return iteratorDeVertices;
	}



	/**
      * Complejidad: O(1) debido se usa una Linkedlist para 
      * guardar los vértices adyacentes
	  */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Integer> adyacentes = new ArrayList<>();
		for (Arco<T> arco : this.matrizAdyacencia.get(verticeId)){
			adyacentes.add(arco.getVerticeDestino());
		}
		return adyacentes.iterator();
	}

	

	/**
      * Complejidad: O(V * A) donde V son todos los vértices del grafo y 
	  *  A son  los arcos salientes desde cada V pero debiendo recorrer a todos los del grafo.
      */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcovich = new ArrayList<>();
		for(Integer key : matrizAdyacencia.keySet()){
			arcovich.addAll(matrizAdyacencia.get(key));
		}
		return arcovich.iterator();
	}

	/**
      * Complejidad: O(1) d debido a que  se utiliza la 
      * función  get(Object key) de HashMap.
      */

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(!this.contieneVertice(verticeId))
			return null;
		return this.matrizAdyacencia.get(verticeId).iterator();
	}

	///////////////////////////////////// TO STRING GRAFO /////////////////////////////////////

	@Override
	public String toString(){
		String data = "";
		for(Integer key: matrizAdyacencia.keySet()){
			data += "Vertice #" + key + "\n";
			for(Arco<T> arco: matrizAdyacencia.get(key)){
				data += "		-> Arco: "+ arco.toString() + "\n";
			}
		}
		return data;
	}

} 
