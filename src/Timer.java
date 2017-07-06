/**
 * Created by belisariops on 7/5/17.
 *
 */
class Timer {
    private long startTime;

    /**
     * Comienza el cronometro.
     */
    void start() {
        this.startTime = System.nanoTime();
    }

    /**
     * Se detiene el cronometro, retorna la diferencia de tiempo en segundos.
     * @return elapsedTime
     */
    double stop() {

        long elapsedTime = System.nanoTime() - startTime;
        return (double)elapsedTime/1000000000.0;
    }
}
