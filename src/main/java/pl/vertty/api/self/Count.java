package pl.vertty.api.self;

public class Count
{
    private final int min;
    private final int max;
    
    public Count(final int min, final int max) {
        this.min = min;
        this.max = max;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    @Override
    public String toString() {
        return this.min + "-" + this.max;
    }
    
    public static Count parse(final String toParse) {
        final String[] split = toParse.split("-");
        int min;
        int max;
        try {
            min = Integer.parseInt(split[0]);
            max = Integer.parseInt(split[1]);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            return new Count(0, 1);
        }
        return new Count(min, max);
    }
}
