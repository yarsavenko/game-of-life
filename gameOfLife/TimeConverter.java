/**
 * Created by Ярослав on 10.06.2017.
 */
public class TimeConverter {
    private static final int NANOSECONDS_IN_MILLISECOND = 1000000;
    public static long nanosTomilos(long nanos){
        return nanos / NANOSECONDS_IN_MILLISECOND;
    }
    public static long milosTonanos(long milos){
        return milos * NANOSECONDS_IN_MILLISECOND;
    }
}
