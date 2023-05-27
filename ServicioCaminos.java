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

		this.obternerCaminos(this.origen, caminosPosibles);
		return  this.caminosQueCumplen;
	}



	
	private void obternerCaminos(int verticeId , List<Integer> caminoParcial) {

		// verificar que el camino parcial no exeda el limite
		if (caminoParcial.size() < this.lim) {

			//Si se encontró el vertice destino
			if (verticeId == this.destino) {
				ArrayList<Integer> aux = new ArrayList<>(); // Guardamos la solucion en un array auxiliar
				aux.addAll(caminoParcial);
				this.caminosQueCumplen.add(aux);  //Agrego el camino posible a la lista de camins que cumplen
			} else {
				Iterator<?> iteratorArcosSalientes = (Iterator<?>) this.grafo.obtenerArcos(verticeId); // Itero los arcos salientes desde verticeId
				while (iteratorArcosSalientes.hasNext()) {
					Arco<?> arcoSaliente = (Arco<?>) iteratorArcosSalientes.next();

					// si el  nuevo arco obtenido por el iterador no fue recorrido
					if (!this.arcosRecorridos.contains(arcoSaliente)) {
						Integer verticeAdyacente = arcoSaliente.getVerticeDestino();  // me quedo con el vertice destino que será mi próximo vertice origen
						caminoParcial.add(verticeAdyacente);
						this.arcosRecorridos.add(arcoSaliente);

						// llamado recursivo
						this.obternerCaminos(verticeAdyacente, caminoParcial);

						caminoParcial.remove(verticeAdyacente);
						this.arcosRecorridos.remove(arcoSaliente);
					}
				}
			}
		}
	}
}
