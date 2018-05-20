package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;
import java.util.Calendar;

/**
 * RI 电影的上映年份year 在[1900,2018]，IMDb评分在[0,10]
 */
public class Movie extends Vertex {
    private int year = 2000; // 电影上映年份
    private String country = "China"; // 电影的拍摄国家
    private double IMDb = 10.0; // IMDb上面的电影评分

    public Movie(String label) {
        super(label);
    }

    private void checkRep() {
        assert (year < 1900 || year > 2018);
        assert (IMDb < 0 || IMDb > 10);
    }

    @Override
    public void fillVertexInfo(String[] args) throws VertexAttributeException {
        if (args.length == 3) {
            try {
                year = Integer.parseInt(args[0]);
                country = args[1];
                IMDb = Double.parseDouble(args[2]);
                IMDb = Double.parseDouble(String.format("%.2f", IMDb));  // 只取小数点后两位

                // 获得当前的年份
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(Calendar.YEAR);
                if (year < 1900 || year > currentYear || IMDb < 0 || IMDb > 10) // 电影上映的年份在[1900,当前年份] IMDb评分在[0,10]之间
                    throw new VertexAttributeException(getLabel());
            } catch (NumberFormatException e) {
                throw new VertexAttributeException(getLabel());
            }
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Movie && ((Movie) obj).getLabel().equals(this.getLabel()) && ((Movie) obj).getCountry().equals(this.getCountry()) && ((Movie) obj).getYear() == this.getYear() && Math.abs(((Movie) obj).getIMDb() - this.getIMDb()) < 0.0001;
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[4];
        objects[0] = this.getLabel();
        objects[1] = this.getYear();
        objects[2] = this.getIMDb();
        objects[3] = this.getCountry();
        return Arrays.hashCode(objects);
    }

    /**
     * 获得电影的上映年份
     *
     * @return 电影上映的年份
     */
    int getYear() {
        return year;
    }

    /**
     * 获得电影的拍摄国家
     *
     * @return 电影的拍摄国家
     */
    String getCountry() {
        return country;
    }

    /**
     * 获得电影的IMDb评分
     *
     * @return 电影的IMDb评分
     */
    double getIMDb() {
        return IMDb;
    }
}
