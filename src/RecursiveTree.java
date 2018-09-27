import java.util.concurrent.RecursiveTask;

public class RecursiveTree extends RecursiveTask<SunData>
{
    int high;
    int low;
    int sequentialCutoff = 1000;
    SunData sundata;

    int rangeMax;
    int rangeMin;

    public RecursiveTree (int h, int l, SunData sundata, int rangeMax, int rangeMin)
    {
        high = h;
        low = l;
        this.sundata = sundata;

        this.rangeMax = rangeMax;
        this.rangeMin = rangeMin;
    }


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
                    synchronized (this) {
                        for (int u = (y - radius); u < (y + radius); u++) {
                            for (int d = (x - radius); d < (x + radius); d++) {
                                try {
                                    suntot += sundata.sunmap.sunlight[u][d];
                                    sundata.sunmap.sunlight[u][d] = sundata.sunmap.sunlight[u][d] * 0.1f;
                                    blocktot++;
                                }
                                catch (ArrayIndexOutOfBoundsException e)
                                {}
                            }
                        }
                    }

                    float avg = suntot / blocktot;
                    t.ext += avg;
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
