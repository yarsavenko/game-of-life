/**
 * Created by Ярослав on 10.06.2017.
 */
public class Delay {
    private long delayNanos;
    private long nanospassed;
    public  Delay(long milos){
        this.delayNanos = TimeConverter.milosTonanos(milos);
    }
    public boolean updateAndCheck(long nanos){
        nanospassed+= nanos;
        if (nanospassed >= delayNanos){
            nanospassed = 0;
            return true;
        }
        else
            return false;
    }
}
