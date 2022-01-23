
public class MinutesFilter implements Filter {
    private int myMin;
    private int myMax;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMin = minMinutes;
        myMax = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        return myMin<=MovieDatabase.getMinutes(id)&&MovieDatabase.getMinutes(id)<=myMax;
    }

}
