import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class RecursiveOperations {

    /**
     * Simulation data
     */
    SunData sundata;
    /**
     * No of simulation increments
     */
    int currentYear = 0;
    /**
     * Whether the simulation is paused or not.
     */
    boolean paused = false;

    /**
     * Parallel pool
     */
    static final ForkJoinPool pool = new ForkJoinPool();

    /**
     * Constructor. Sets sundata.
     * @param sd    Simulation data
     */
    public RecursiveOperations(SunData sd)
    {
        sundata = sd;
    }


    /**
     * Returns the max extent form the simulation forest
     * @param sd Simulation data
     * @return Highest extent in the Tree array
     */
    public float getMaxExtent(SunData sd)
    {
        float max = pool.invoke(new RecursiveMax(sundata.trees.length, 0, sundata));
        return max;
    }

    /**
     * Sets pause to true.
     */
    public void pause(){
        paused = true;
    }

    /**
     * Sets pause to false.
     */
    public void resume(){
        paused = false;
    }

    /**
     * Runs the simulation for a single year, if paused is equal to false.
     * @return Simulation results.
     */


    public SunData incrementYear(){
        if (!paused) {
            float extHigh = this.getMaxExtent(sundata);
            double limit = Math.ceil((double) extHigh);
            if ((int) limit % 2 != 0) {
                limit = limit + 1;
            }

            for (int i = (int) limit; i > 0; i = i - 2) {
                int rangeMax = i;
                int rangeMin = i - 2;
                pool.invoke(new RecursiveTree(sundata.trees.length, 0, sundata, rangeMax, rangeMin));
            }
            currentYear++;
            sundata.resetSunlight();
        }

        return sundata;



    }
}
