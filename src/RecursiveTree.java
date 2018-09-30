import java.util.concurrent.RecursiveTask;

public class RecursiveTree extends RecursiveTask<SunData>
{
    /**
     * High array position
     */
    int high;
    /**
     * Low array position
     */
    int low;
    /**
     * Sequential Cutoff
     */
    int sequentialCutoff = 1000;
    /**
     * Simulation data
     */
    SunData sundata;

    /**
     * Maximum extent to look for
     */
    int rangeMax;
    /**
     * Minimum extent to look for
     */
    int rangeMin;

    /**
     * First X Grid boundary
     */
    int xSplit1;
    /**
     * Second X Grid boundary
     */
    int xSplit2;

    /**
     * First Y Grid boundary
     */
    int ySplit1;
    /**
     * Second Y Grid boundary
     */
    int ySplit2;

    /**
     * Constructor. Sets the values of rangeMax, rangeMin, high, low, sundata, xSplit1, xSplit2, ySplit1, and ySplit2.
     * @param h High
     * @param l Low
     * @param sundata Simulation data
     * @param rangeMax Highest extent to look for
     * @param rangeMin Lowest extent to look for
     */
    public RecursiveTree (int h, int l, SunData sundata, int rangeMax, int rangeMin)
    {
        high = h;
        low = l;
        this.sundata = sundata;

        this.rangeMax = rangeMax;
        this.rangeMin = rangeMin;

        int xSplit1 = sundata.sunmap.x / 3;
        int xSplit2 = sundata.sunmap.x - xSplit1;

        int ySplit1 = sundata.sunmap.y / 3;
        int ySplit2 = sundata.sunmap.y - ySplit1;
    }


    /**
     * Parallel method. Grows trees in the extent bracket, and shades the ground beneath them
     * @return Simulation results
     */
    @Override
    protected SunData compute() {
        if (high - low < sequentialCutoff)
        {
            for (int i = low; i < high; i++)
            {
                Tree t = sundata.trees[i];
                if (t.ext >= rangeMin && t.ext < rangeMax)
                {
                    int x = t.getX();
                    int y = t.getY();
                    float ext = t.getExt();
                    int radius = (int)Math.ceil(ext);
                    float suntot = 0;
                    float blocktot = 0;
                    //lock these whole loops
                    if (x <= xSplit1) {
                        if (y <= ySplit1) {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else if (y <= ySplit2)
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (x <= xSplit2)
                    {
                        if (y <= ySplit1)
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else if (y <= ySplit2)
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if (y <= ySplit1)
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else if (y <= ySplit2)
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            synchronized (this) {
                                for (int u = (y - radius); u < (y + radius); u++) {
                                    for (int d = (x - radius); d < (x + radius); d++) {
                                        try {
                                            suntot += sundata.sunmap.sunlight[u][d];
                                            sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                            blocktot++;
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                        }
                                    }
                                }
                            }
                        }
                    }

                    float avg = suntot / blocktot;
                    t.ext += (avg / t.growfactor);
                    sundata.trees[i] = t;
                }
            }
            return null;
        }
        else {

            RecursiveTree left = new RecursiveTree((high + low) / 2, low, sundata, rangeMax, rangeMin);
            RecursiveTree right = new RecursiveTree(high, (high + low) / 2, sundata, rangeMax, rangeMin);

            left.fork();
            right.compute();

            return null;
        }
    }
}
