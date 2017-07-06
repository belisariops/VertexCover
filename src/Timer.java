/**
 * Created by belisariops on 7/5/17.
 */
public class Timer {
    private long startTime;
    private long elapsedTime;

    /**
     * Comienza el cronometro.
     */
    public void start() {
        this.startTime = System.nanoTime();
    }

    /**
     * Se detiene el cronometro, retorna la diferencia de tiempo en segundos.
     * @return elapsedTime
     */
    public double stop() {
        this.elapsedTime = System.nanoTime() - startTime;
        return (double)elapsedTime/1000000000.0;
    }


}
