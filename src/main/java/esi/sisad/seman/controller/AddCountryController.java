package esi.sisad.seman.controller;

import esi.sisad.seman.model.Country;
import esi.sisad.seman.service.CountryService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AddCountryController {

    @FXML
    private TextField country_txt;

    @FXML
    private TextField capital_txt;

    @FXML
    private TextField population_txt;

    private CountryService countryService = null;

    @FXML
    private void initialize() {
        country_txt.setText("");
        capital_txt.setText("");
        population_txt.setText("");
        countryService = new CountryService();
    }

    @FXML
    private void onAdd() throws IOException {
        Country country = new Country();
        country.setName(country_txt.getText());
        country.setCapital(capital_txt.getText());
        Integer population = population_txt.getText().isEmpty() ?
                             Integer.valueOf("0") :
                             Integer.valueOf(population_txt.getText());
        country.setPopulation(population);

        countryService.save(country);

        StackPane book = FXMLLoader.load(getClass().getResource("/templates/country.fxml"));
        CountryController.root_stackpane.getChildren().setAll(book);
    }

    @FXML
    private void onClear() {
        country_txt.setText("");
        capital_txt.setText("");
        population_txt.setText("");
    }

    @FXML
    private void onCancel() throws IOException {
        StackPane book = FXMLLoader.load(getClass().getResource("/templates/country.fxml"));
        CountryController.root_stackpane.getChildren().setAll(book);
    }
}
