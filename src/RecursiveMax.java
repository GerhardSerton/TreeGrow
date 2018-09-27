import java.util.concurrent.RecursiveTask;

public class RecursiveMax extends RecursiveTask<Float> {

    int high;
    int low;
    SunData sd;
    float max = 0;
    int sequentialCutoff = 1000;

    public RecursiveMax(int h, int l, SunData sd){
        high = h;
        low = l;
        this.sd = sd;
    }
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
