package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";

    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String DELETE_ALL_CARS = "DELETE FROM car";
    private static final String SELECT_ALL_CARS = "SELECT * FROM car";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";


    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deleteByIdPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private final PreparedStatement findAllModelsPreStmt;
    private final PreparedStatement deleteAllPreStmt;


    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        this.findAllModelsPreStmt = connection.prepareStatement(SELECT_ALL_CARS);
        this.deleteAllPreStmt=connection.prepareStatement(DELETE_ALL_CARS);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> carSet = new HashSet<>();
try (PreparedStatement statement=createPreStmt){
    for (Car car : cars) {
        statement.setString(1, car.getId());
        statement.setString(2, car.getModel());

        statement.executeUpdate();
        carSet.add(car);
    }
}catch (Exception e){
    throw new RuntimeException();
}

        return carSet;
    }

    @Override
    public Set<Car> findAll()  {
        Set<Car> carSet = new HashSet<>();
        try(PreparedStatement statement=findAllModelsPreStmt) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                carSet.add(car);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return carSet;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
        try (PreparedStatement statement=deleteByIdPreStmt){
            statement.setString(1,id);
            int affectedRows=statement.executeUpdate();
            return affectedRows > 0;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean deleteAll() {
        try (PreparedStatement statement = deleteAllPreStmt){
            int affectedRows=statement.executeUpdate();
            return affectedRows > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> carSet = new HashSet<>();
        try(PreparedStatement statement=findByModelPreStmt){

        statement.setString(1, model);
        ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                carSet.add(car);

            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return carSet;
    }
}
