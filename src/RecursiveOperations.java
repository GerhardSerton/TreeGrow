import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class RecursiveOperations {

    SunData sundata;
    SunData ogSundata;
    float currentHigh = 999999999;
    float currentLow = 0;
    int currentYear = 0;
    boolean paused = false;

    static final ForkJoinPool pool = new ForkJoinPool();

    public RecursiveOperations(SunData sd)
    {
        sundata = sd;
    }


    public float getMaxExtent(SunData sd)
    {
        float max = pool.invoke(new RecursiveMax(sundata.trees.length, 0, sundata));
        return max;
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }



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
