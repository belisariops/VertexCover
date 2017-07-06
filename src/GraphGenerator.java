import java.util.*;

/**
 * Created by Belisario Panay, Americo Ferrada on 7/4/17.
 * Generador aleatorio de grafos, respetando la estructura del grafo.
 */

class GraphGenerator {

    /**
     * Se crea un grafo aleatorio de tamano n, con probabilida de que exista una arista de p.
     * Las aristas son creadas aleatoriamente segun el enunciado,  luego se les hace un post proceso
     * para que respeten la estructura de la implementacion.
     * @param n numero de vertices.
     * @param p numero de aristas.
     * @return retorna un grafo con la probabilidad asignada.
     */
    Graph create(int n, double p) {
        List<Vertex> vertices = new ArrayList<>(n);

        Random r = new Random(System.currentTimeMillis());
        float chance;
        int m;

        /*Se crean las aristas con probabilidad p*/
        Edge randEdge;

        List<List<Edge>> buckets = new ArrayList<>(n);

        for (int i =0; i<n; i++)
            buckets.add(new ArrayList<>(n-1));


        /*Segun enunciado*/
        for(int j=0; j < n ; j++) {
            for (int k = j+1; k < n; k++) {
                chance = r.nextFloat();
                if (chance < p) {
                    randEdge = new Edge();
                    randEdge.src = j;
                    randEdge.tgt = k;
                    randEdge.cmp = -1;
                    buckets.get(j).add(randEdge);

                }
            }
        }

        /*Se crean las aristas con las dos direcciones (ej: (u,v) y (v,u)), para la lista de adyacencia*/
        boolean foundEdge = false;

        for (int i =0; i<n; i++) {
            m = buckets.get(i).size();
            for (int j =0; j < m; j++) {
                Edge bucketEdge = buckets.get(i).get(j);
                if (bucketEdge.src == bucketEdge.tgt)
                    continue;
                List<Edge> otherBucket = buckets.get(bucketEdge.tgt);

                for(Edge thisEdge: otherBucket){
                    if (thisEdge.tgt == bucketEdge.src) {
                        foundEdge = true;
                        break;
                    }
                }


                Edge mirrorEdge = new Edge();
                mirrorEdge.src = bucketEdge.tgt;
                mirrorEdge.tgt = bucketEdge.src;
                mirrorEdge.cmp = -1;

                if (!foundEdge)
                    buckets.get(bucketEdge.tgt).add(mirrorEdge);
                foundEdge = false;
            }
        }

        for(List<Edge> bucket: buckets) {
            bucket.sort(Comparator.comparing(Edge::getTgt));
        }

        ArrayList<Edge> sortedY = this.merge(this.invert(buckets));


        Vertex v;
        int position=0;
        for (List<Edge> bucket : buckets) {
            v = new Vertex();
            v.first = position;
            v.last = bucket.size() + position - 1;

            if (v.first > v.last) {
                v.first =-1;
                v.last = -2;
                //f++;
                vertices.add(v);
                continue;
            }
            vertices.add(v);
            //edges.addAll(bucket);
            position = v.last+1;
        }


        /*for (Vertex vert : vertices)
            System.out.println("("+vert.first+","+vert.last+")");
        System.out.println("----------------------------------------");*/


        /*m = edges.size();
        Vertex target;
        boolean cmpFound = false;
        for (int i=0; i<m; i++) {
            target = vertices.get(edges.get(i).tgt);

            for (int j = target.first; j <= target.last; j++) {
                if (j < 0)
                    continue;
                if (edges.get(j).cmp != -1) {
                    cmpFound = true;
                    continue;
                }
                if (edges.get(j).tgt == edges.get(i).src) {
                    edges.get(j).cmp = i;
                    edges.get(i).cmp = j;
                    cmpFound = true;
                    break;
                }
            }
            if (!cmpFound)
                System.out.println("No se encontro arista espejo de ("+edges.get(i).src+","+edges.get(i).tgt+")");
            cmpFound = false;
        }*/

        /*for (Edge e : edges)
            System.out.println("("+e.src+","+e.tgt+") cmp="+ e.cmp);*/

       return new Graph(vertices,sortedY);
    }

    /**
     * Toma un arreglo de aristas, las invierte y les asigna el indice en que se encontraban antes de la inversion.
     * @param buckets listas por vertice.
     * @return la misma lista con cada Edge con sus valores invertidos.
     */
    private ArrayList<ArrayList<Edge>> invert(List<List<Edge>> buckets) {
        ArrayList<ArrayList<Edge>> result = new ArrayList<>();
        int acc = 0;
        for (List<Edge> list : buckets){
            ArrayList<Edge> element = new ArrayList<>();
            for(Edge e: list){
                Edge ed = new Edge();
                ed.tgt = e.src;
                ed.src = e.tgt;
                ed.cmp = acc + list.indexOf(e);
                element.add(ed);
            }
            result.add(element);
            acc += list.size();
        }

        return result;
    }

    /**
     * Junta  ordena los buckets manteniendo los ordenes parciales.
     * @param list lista a ordenar
     * @return retorna la lista mezclada y ordenada.
     */
    private ArrayList<Edge> merge(ArrayList<ArrayList<Edge>> list){
        if(list.size() == 1){
            return list.get(0);
        }
        return recMerge(new ArrayList<>(list.subList(0,list.size()/2)),new ArrayList<>(list.subList(list.size()/2,list.size())));
    }

    /**
     * Dada dos listas las junta, ordenando establemente por el primer valor.
     * @param list1 lista 1.
     * @param list2 lista 2.
     * @return una lista con las dos oiginales mezcladas y ordenadas.
     */
    private ArrayList<Edge> recMerge(ArrayList<ArrayList<Edge>> list1, ArrayList<ArrayList<Edge>> list2){
        ArrayList<Edge> result = new ArrayList<>();
        ArrayList<Edge> result1;
        ArrayList<Edge> result2;
        if(list1.size()>1){
            result1 = recMerge(new ArrayList<>(list1.subList(0,list1.size()/2)),new ArrayList<>(list1.subList(list1.size()/2,list1.size())));
        }else{
            result1 = list1.get(0);
        }
        if(list2.size()>1){
            result2 = recMerge(new ArrayList<>(list2.subList(0,list2.size()/2)),new ArrayList<>(list2.subList(list2.size()/2,list2.size())));
        }else{
            result2 = list2.get(0);
        }
        int i = 0, j = 0;
        while(i < result1.size() || j < result2.size()){
            if(i < result1.size() && j < result2.size()) {
                if (result1.get(i).src <= result2.get(j).src) {
                    result.add(result1.get(i));
                    i++;
                } else {
                    result.add(result2.get(j));
                    j++;
                }
            }else{
                if(i == result1.size()){
                    result.add(result2.get(j));
                    j++;
                }else{
                    result.add(result1.get(i));
                    i++;
                }
            }
        }

        return  result;
    }


}
