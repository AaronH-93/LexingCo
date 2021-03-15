package models;

public class Car {
    CarPart battery;
    CarPart brakes;
    CarPart engine;
    CarPart clutch;
    CarPart Transmission;
    CarPart Axle;
    CarPart Chassis;
    CarPart tyres;
    CarPart injectors;

    public Car(CarPart battery, CarPart brakes, CarPart engine, CarPart clutch, CarPart transmission, CarPart axle, CarPart chassis, CarPart tyres, CarPart injectors) {
        this.battery = battery;
        this.brakes = brakes;
        this.engine = engine;
        this.clutch = clutch;
        this.Transmission = transmission;
        this.Axle = axle;
        this.Chassis = chassis;
        this.tyres = tyres;
        this.injectors = injectors;
    }

    public CarPart getBattery() {
        return battery;
    }

    public void setBattery(CarPart battery) {
        this.battery = battery;
    }

    public CarPart getBrakes() {
        return brakes;
    }

    public void setBrakes(CarPart brakes) {
        this.brakes = brakes;
    }

    public CarPart getEngine() {
        return engine;
    }

    public void setEngine(CarPart engine) {
        this.engine = engine;
    }

    public CarPart getClutch() {
        return clutch;
    }

    public void setClutch(CarPart clutch) {
        this.clutch = clutch;
    }

    public CarPart getTransmission() {
        return Transmission;
    }

    public void setTransmission(CarPart transmission) {
        Transmission = transmission;
    }

    public CarPart getAxle() {
        return Axle;
    }

    public void setAxle(CarPart axle) {
        Axle = axle;
    }

    public CarPart getChassis() {
        return Chassis;
    }

    public void setChassis(CarPart chassis) {
        Chassis = chassis;
    }

    public CarPart getTyres() {
        return tyres;
    }

    public void setTyres(CarPart tyres) {
        this.tyres = tyres;
    }

    public CarPart getInjectors() {
        return injectors;
    }

    public void setInjectors(CarPart injectors) {
        this.injectors = injectors;
    }
}
