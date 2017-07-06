/**
 * Created by Belisario Panay, Americo Ferrada on 7/3/17.
 * Clase que representa una arista, donde se guarda la posicion de un vertice
 * source, otro target y la posicion de la arista complementaria en el arreglo
 * de aristas.
 */
public class Edge {
    public int src;
    public int tgt;
    public int cmp;

    /**
     * Getter utilizado en el comprador del sort.
     * @return
     */
    public int getTgt(){
        return tgt;
    }

}
