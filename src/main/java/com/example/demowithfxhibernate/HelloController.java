package com.example.demowithfxhibernate;

import com.example.demowithfxhibernate.model.Car;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    EntityManagerFactory entityManagerFactory = HelloApplication.ENTITY_MANAGER_FACTORY;

    /**
     * FXML related properties and methods
     * */

    @FXML private TableView<Car> carsTable;
    @FXML private TableColumn<Car, Integer> idColumn;
    @FXML private TableColumn<Car, String> brandColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, Integer> priceColumn;
    @FXML private TableColumn<Car, Boolean> retiredColumn;
    @FXML private TextField carBrandTextField;
    @FXML private TextField carModelTextField;
    @FXML private TextField carPriceTextField;
    @FXML private ToggleButton carRetiredToggle;
    @FXML private Button saveNewCarButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("carId"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("price"));
        retiredColumn.setCellValueFactory(new PropertyValueFactory<Car, Boolean>("retired"));

        carsTable.getItems().setAll(getAllCars());

        carsTable.getSelectionModel().selectedItemProperty().addListener(((observableValue, car, t1) -> {
            carBrandTextField.setText(carsTable.getSelectionModel().getSelectedItem().getBrand());
            carModelTextField.setText(carsTable.getSelectionModel().getSelectedItem().getModel());
            carPriceTextField.setText(String.valueOf(carsTable.getSelectionModel().getSelectedItem().getPrice()));
            carRetiredToggle.setSelected(carsTable.getSelectionModel().getSelectedItem().isRetired());
        }));
    }



    public void saveNewCarButtonWasClicked() {
        Car carToAdd = new Car(carBrandTextField.getText(), carModelTextField.getText(), Integer.valueOf(carPriceTextField.getText()), carRetiredToggle.isSelected());
        addCar(carToAdd);
    }

    /**
     * Database related methods to perform CRUD operations
     * */

    // Använder Entity managerns persist-method
    public boolean addCar(Car theCar) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        boolean isSuccess = true;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(theCar);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSuccess = false;

        } finally {
            entityManager.close();
        }
        return isSuccess;
    }


    // Använder TypedQuery<Car> och entitymanagerns createQuery för att hämta alla bilar.
    public List<Car> getAllCars() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<Car> carList = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Car> allCarsQuery = entityManager.createQuery("from Car", Car.class);
            carList = allCarsQuery.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return carList;

    }
}