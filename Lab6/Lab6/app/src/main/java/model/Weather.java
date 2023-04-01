package model;

public class Weather {
     int img;
    double temp;
    String name;

    public Weather(int img, double temp, String name) {
        this.img = img;
        this.temp = temp;
        this.name = name;
    }

    public int getImg() {
        return img;
    }


    public double getTemp() {
        return temp;
    }


    public String getName() {
        return name;
    }

}
