/**
 * Created by Belisario Panay, Americo Ferrada on 7/3/17.
 * Clase que representa una arista, donde se guarda la posicion de un vertice
 * source, otro target y la posicion de la arista complementaria en el arreglo
 * de aristas.
 */
class Edge {
    int src;
    int tgt;
    int cmp;

    /**
     * Getter utilizado en el comprador del sort.
     * @return tgt
     */
    int getTgt(){
        return tgt;
    }

}
