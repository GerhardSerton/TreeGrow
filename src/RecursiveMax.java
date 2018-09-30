import java.util.concurrent.RecursiveTask;

public class RecursiveMax extends RecursiveTask<Float> {

    /**
     * High array position
     */
    int high;
    /**
     * Low array position
     */
    int low;
    /**
     * Simulation data
     */
    SunData sd;
    /**
     * Current highest extent
     */
    float max = 0;
    /**
     * Sequential cutoff
     */
    int sequentialCutoff = 1000;

    /**
     * Constructor. Sets high, low, and sd.
     * @param h High
     * @param l Low
     * @param sd Simulation data
     */

    public RecursiveMax(int h, int l, SunData sd){
        high = h;
        low = l;
        this.sd = sd;
    }

    /**
     * Parallel portion. Looks through the Tree array, and returns the highest extent.
     * @return
     */
    @Override
    protected Float compute() {
        if (high - low < sequentialCutoff)
        {
            for (int i = low; i < high; i++)
            {
                if (sd.trees[i].ext > max)
                {
                    max = sd.trees[i].ext;
                }
            }
            return max;
        }

        RecursiveMax left = new RecursiveMax((high + low) / 2, low, sd);
        RecursiveMax right = new RecursiveMax(high, (high + low) / 2, sd);

        left.fork();
        float rightAnswer = right.compute();
        float leftAnswer = left.join();

        if (rightAnswer >= leftAnswer)
        {
            return rightAnswer;
        }
        return leftAnswer;
    }
}
