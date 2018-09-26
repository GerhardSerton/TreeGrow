import java.util.concurrent.ForkJoinPool;

public class RecursiveOperations {

    SunData sundata;
    float currentHigh = 999999999;
    float currentLow = 0;
    int currentYear = 0;

    static final ForkJoinPool pool = new ForkJoinPool();

    public RecursiveOperations(SunData sd)
    {
        sundata = sd;
    }


    public void organiseTreesQuickSort()
    {
        quickSort(0, sundata.trees.length - 1);
    }

    public void quickSort(int low, int high)
    {
        int ogLow = low;
        int ogHigh = high;

        float p = sundata.trees[low + (high - low)/2].ext;

        while (low <= high)
        {
            while (sundata.trees[low].ext < p)
            {
                low++;
            }
            while (sundata.trees[high].ext > p)
            {
                high--;
            }
            if (low <= high)
            {
                float t = sundata.trees[low].ext;
                sundata.trees[low].ext = sundata.trees[high].ext;
                sundata.trees[high].ext = t;
            }
        }

        if (ogLow < high)
            quickSort(ogLow, high);
        if (low < ogHigh)
            quickSort(low,ogHigh);
    }

    public SunData incrementYear(){
        organiseTreesQuickSort();
        float extHigh = sundata.trees[sundata.trees.length - 1].ext;
        double limit = Math.ceil((double)extHigh);
        if ((int)limit % 2 != 0)
        {
            limit = limit + 1;
        }

        for (int i = (int)limit; i > 0; i = i - 2)
        {
            int rangeMax = i;
            int rangeMin = i - 2;
            pool.invoke(new RecursiveTree(sundata.trees.length, 0, sundata, rangeMax, rangeMin));
        }
        currentYear++;

        return sundata;



    }
}
