package Assignment5;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class CarXcoordinates implements Comparable<CarXcoordinates> {
    private int xCarCoordinate;
    private int carNumber;


    public CarXcoordinates() {
    }

    public CarXcoordinates(int xCarCoordinate, int carNumber) {
        this.xCarCoordinate = xCarCoordinate;
        this.carNumber = carNumber;
    }

    public int getxCarCoordinate() {
        return xCarCoordinate;
    }

    public void setxCarCoordinate(int xCarCoordinate) {
        this.xCarCoordinate = xCarCoordinate;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public void setCar(CarDrawer carList) {
        this.xCarCoordinate = carList.getxCar();
        this.carNumber = new Integer(carList.getCarNum());
    }

    @Override
    public int compareTo(CarXcoordinates car) {
        return this.getxCarCoordinate() > (car.getxCarCoordinate()) ? -1:this.getxCarCoordinate() < (car.getxCarCoordinate())? +1 : 0;
    }


}
