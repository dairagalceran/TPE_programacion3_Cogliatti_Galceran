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
		if(this.contieneVertice(verticeId)){
			this.matrizAdyacencia.remove(verticeId);
			for (Integer key : matrizAdyacencia.keySet()){

				for(Arco<T> arco : matrizAdyacencia.get(key)){
					if(arco.getVerticeDestino() == verticeId)
						this.matrizAdyacencia.get(key).remove(arco);
				}
			}
		}
	}
	
	/**
      * Complejidad: O(2a) = O(a) debido a que deben verificar si existe  el Arco
      * "buscar el tamaño de las claves del hashMap  de la matrizAdyacencia
      */

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		this.agregarVertice(verticeId1); // O(1)
		this.agregarVertice(verticeId2);// O(1)
		ArrayList<Arco<T>> arcosSalientesDelVertice1 = this.matrizAdyacencia.get(verticeId1);// O(1)
		boolean existeArcoSaliente = this.existeArco(verticeId1, verticeId2); // O(a)
		if(!existeArcoSaliente){
			arcosSalientesDelVertice1.add(new Arco<T>(verticeId1, verticeId2, etiqueta));// O(a)
		}	
	}

	//????????????

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcosSalientesDelVertice1 = this.matrizAdyacencia.get(verticeId1);
		if(arcosSalientesDelVertice1 != null){
			Iterator<Arco<T>> iteratorArcosSalientes =  arcosSalientesDelVertice1.iterator();
			ArrayList<Arco<T>> arcosQueNoQuieroBorrar = new ArrayList<Arco<T>>();
			while(iteratorArcosSalientes.hasNext() ){
				Arco<T> arcoSaliente = iteratorArcosSalientes.next();
				if(arcoSaliente.getVerticeDestino() != verticeId2){
					arcosQueNoQuieroBorrar.add(arcoSaliente);
				}
			}
			this.matrizAdyacencia.replace(verticeId1, arcosQueNoQuieroBorrar);
		}
		

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
		//obtener la clave del hashMap = O(1)
		ArrayList<Arco<T>> arcosSalientesDelVertice1 = this.matrizAdyacencia.get(verticeId1); //O(1)

		if(arcosSalientesDelVertice1 != null){
			Iterator<Arco<T>> iteratorArcosSalientes =  arcosSalientesDelVertice1.iterator(); //O(1)
			
			//Recorrer ArrayList de Arco<T> con vertice origen = vId1 -> O(a)
			while(iteratorArcosSalientes.hasNext() ){
				Arco<T> arcoSaliente = iteratorArcosSalientes.next();
				if(arcoSaliente.getVerticeDestino() == verticeId2){
					return arcoSaliente;
				}
			}	
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
		int cantidadTotalArcos = 0;
		
		Iterator<Integer> iteratorVertices = this.obtenerVertices(); // O(1)
		
		while(iteratorVertices.hasNext()){
			Integer vertice = iteratorVertices.next();  //O(V) recorre todos los vertices
			ArrayList<Arco<T>> listadoDeArcosSaliente = this.matrizAdyacencia.get(vertice);  //O(1) siendo a la cantidad máxima de arcos salientes desde cad vertice
			Integer cantidadArcosSalientesVertice = listadoDeArcosSaliente.size(); // O(1)
			cantidadTotalArcos += cantidadArcosSalientesVertice; // O(1)
		}
		return cantidadTotalArcos;
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
		
		LinkedList<Integer> listadoVerticesAdyacentes = new LinkedList<Integer>(); //creo una variable para guardar los vértices adyacentes	
		Iterator<Arco<T>> iteratorArcosSalientes =  this.obtenerArcos(verticeId); // O(1)
		
			while(iteratorArcosSalientes.hasNext()){
				Arco<T> arcosSaliente = iteratorArcosSalientes.next();// O(1) 
				Integer verticeAdyacente = arcosSaliente.getVerticeDestino(); // O(1)
				listadoVerticesAdyacentes.add(verticeAdyacente); // O(1) por usar linkedList , si uso ArrayList O(V)
			}
		return listadoVerticesAdyacentes.iterator();
	}

	

	/**
      * Complejidad: O(V * A) donde V son todos los vértices del grafo y 
	  *  A son  los arcos salientes desde cada V pero debiendo recorrer a todos los del grafo.
      */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> listadoArcos = new ArrayList<Arco<T>>();
		Iterator<Integer> iteratorVertices = this.obtenerVertices();

		while(iteratorVertices.hasNext()){
			Integer vertice = 	iteratorVertices.next();
			Iterator<Arco<T>>  iteratorArcosSalientes = this.obtenerArcos(vertice); // O(V)obtener la lista de arcos
			while(iteratorArcosSalientes.hasNext()){
				Arco<T> arcoSaliente = iteratorArcosSalientes.next();
				listadoArcos.add(arcoSaliente); //O(A)   //agrega los arcos salientes a la lista de arcos
			}
		}
		return listadoArcos.iterator(); //retorna iterator de todos los arcos
	}

	/**
      * Complejidad: O(1) d debido a que  se utiliza la 
      * función  get(Object key) de HashMap.
      */

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> listadoDeArcosSaliente = this.matrizAdyacencia.get(verticeId);  
		ArrayList<Arco<T>> listadoVacio = new ArrayList<>();
		if(listadoDeArcosSaliente != null){
			return listadoDeArcosSaliente.iterator(); //O(1)retorna iterator de arcos salientes desde el vertice pasado por parámetro
		}
		return listadoVacio.iterator() ;
	}

//	@Override
//	public void imprimirGrafo(){
//		for(int v: matrizAdyacencia.keySet()){
//			System.out.print(v+ ": ");
//
//			for(Arco<T> arco: matrizAdyacencia.get(v)){
//				System.out.print(arco.getVerticeDestino() + "( " + arco.getEtiqueta()+ ") ");
//
//			}
//			System.out.println();
//		}
//	}

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
