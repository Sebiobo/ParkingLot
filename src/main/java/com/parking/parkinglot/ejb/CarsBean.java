package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CarsBean {
    private static final Logger LOG= Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public CarDto findById(Long id) {
        LOG.info("findById");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c WHERE c.id=:id", Car.class);
            List<Car> cars = typedQuery.setParameter("id", id).getResultList();

            if (cars.isEmpty()) {
                return null;
            }

            Car car = cars.get(0);


            CarDto carDto = new CarDto(
                    car.getId(),
                    car.getLincensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );

            return carDto;

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : cars) {
            CarDto dto = new CarDto(
                    car.getId(),
                    car.getLincensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );
            carDtos.add(dto);
        }
        return carDtos;
    }
    public void createCar(String licensePlate, String parkingSpot, Long userId){
        LOG.info("createCar");
        Car car = new Car();
        car.setLincensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user=entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }
    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId){
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class, carId);
        car.setLincensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser=car.getOwner();
        oldUser.getCars().remove(car);

        User user=entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
    }
    public void deleteCarsByIds(Collection<Long> carIds){
        LOG.info("deleteCar");

        for(Long carId:carIds){
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
    }
}
