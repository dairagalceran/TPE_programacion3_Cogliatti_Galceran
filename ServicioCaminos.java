import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
    
    private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private List<List<Integer>> caminosQueCumplen;
	private HashSet<Arco<?>> arcosRecorridos;
	


	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.caminosQueCumplen = new ArrayList<>();
		this.arcosRecorridos = new HashSet <>();
	}



	public List<List<Integer>> caminos() {
		
		List<Integer> caminosPosibles = new ArrayList<Integer>();
		caminosPosibles.add(this.origen);
		

		this.obternerCaminos(this.origen , this.destino, this.lim, caminosPosibles);
			

			System.out.println("camino cumple en caminos() "+this.caminosQueCumplen);
		return  this.caminosQueCumplen;
	}



	
	private void obternerCaminos(int verticeId , int destino,  int lim, List<Integer> caminoParcial){

		
		System.out.println("-----------");
		System.out.println("origen: "+verticeId+" - destino: "+destino+ " caminos posibles "+ caminoParcial);
		

		if(verticeId ==  this.destino){ //Si se encontró el vertice destino
			ArrayList<Integer> aux = new ArrayList<>();
			aux.addAll(caminoParcial);//*Guardamos la solucion
																			System.out.println("----------");
																			System.out.println("dentro del if origen = destino ");
																			System.out.println("caminoParcial =>  " + caminoParcial);
			this.caminosQueCumplen.add(aux);  //Agrego el camino posible a la lista de camins que cumplen
																			System.out.println("camino cumple "+ this.caminosQueCumplen);
																			System.out.println("camino  parcial del if cumple "+ caminoParcial);
																
		}else{

			Iterator<?> iteratorArcosSalientes = (Iterator<?>) this.grafo.obtenerArcos(verticeId); // Itero los arcos salientes desde verticeId

			while(iteratorArcosSalientes.hasNext()){

				Arco<?> arcoSaliente = (Arco<?>) iteratorArcosSalientes.next();
																System.out.println(" arco saliente "+arcoSaliente);
				if(!this.arcosRecorridos.contains(arcoSaliente) && caminoParcial.size() < lim ){ // si el  nuevo arco obtenido por el iterador no fue recorrido

					Integer verticeAdyacente = arcoSaliente.getVerticeDestino();  // me quedo con el vertice destino que será mi próximo vertice origen

					caminoParcial.add(verticeAdyacente);
				    this.arcosRecorridos.add(arcoSaliente);					
					this.obternerCaminos(verticeAdyacente, destino, lim, caminoParcial);
					caminoParcial.remove(verticeAdyacente);
				    this.arcosRecorridos.remove(arcoSaliente);
					
				}
				
			}	
			
		}
		
	}


	
}
