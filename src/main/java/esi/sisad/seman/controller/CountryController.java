package esi.sisad.seman.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import esi.sisad.seman.cell.EditingCell;
import esi.sisad.seman.model.Country;
import esi.sisad.seman.service.CountryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import lombok.Getter;

import java.io.IOException;


public class CountryController {
    public static CountryController instance;
    public static StackPane root_stackpane;
    @FXML
    @Getter
    private StackPane root_book;
    @FXML
    private TextField search_txt;
    @FXML
    private TableView<Country> countries_table;
    @FXML
    private TableColumn<Country, String> country_column;
    @FXML
    private TableColumn<Country, String> capital_column;
    @FXML
    private TableColumn<Country, String> population_column;
    @FXML
    private TableColumn<Country, Void> actions_column;

    private CountryService countryService = null;
    private ObservableList<Country> countries = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        countryService = new CountryService();
        countries = this.getCountries();

        instance = this;

        countries_table.setPlaceholder(new Label("Aucun pays.."));

        countries_table.setEditable(true);

        createTableView();

        FilteredList<Country> filteredCountries = new FilteredList<>(countries, p -> true);
        search_txt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCountries.setPredicate(country -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (country.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return country.getCapital().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });
        SortedList<Country> sortedCountries = new SortedList<>(filteredCountries);
        sortedCountries.comparatorProperty().bind(countries_table.comparatorProperty());
        countries_table.setItems(sortedCountries);
    }

    private void createTableView() {
        Callback<TableColumn<Country, String>, TableCell<Country, String>> countryCellFactory =
            param -> new EditingCell();
        country_column.setCellFactory(countryCellFactory);
        country_column.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getName();
            return new SimpleStringProperty(name);
        });
        country_column.setOnEditCommit(event ->
                                           event.getTableView()
                                                .getItems().get(event.getTablePosition().getRow())
                                                .setName(event.getNewValue()));

        Callback<TableColumn<Country, String>, TableCell<Country, String>> capitalCellFactory =
            param -> new EditingCell();
        capital_column.setCellFactory(capitalCellFactory);
        capital_column.setCellValueFactory(cellData -> {
            String capital = cellData.getValue().getCapital();
            return new SimpleStringProperty(capital);
        });
        capital_column.setOnEditCommit(event ->
                                           event.getTableView()
                                                .getItems().get(event.getTablePosition().getRow())
                                                .setCapital(event.getNewValue()));

        Callback<TableColumn<Country, String>, TableCell<Country, String>> populationCellFactory =
            param -> new EditingCell();
        population_column.setCellFactory(populationCellFactory);
        population_column.setCellValueFactory(cellData -> {
            Integer population = cellData.getValue().getPopulation();
            return new SimpleStringProperty(String.valueOf(population));
        });
        population_column.setOnEditCommit(event ->
                                              event.getTableView()
                                                   .getItems().get(event.getTablePosition().getRow())
                                                   .setPopulation(Integer.valueOf(event.getNewValue())));

        Callback<TableColumn<Country, Void>, TableCell<Country, Void>> columnActionFactory =
            new Callback<TableColumn<Country, Void>, TableCell<Country, Void>>() {
                @Override
                public TableCell<Country, Void> call(TableColumn<Country, Void> param) {
                    return new TableCell<Country, Void>() {
                        final HBox hBox = new HBox();
                        final JFXButton updateButton = new JFXButton("");
                        final JFXButton deleteButton = new JFXButton("");

                        {
                            hBox.setSpacing(10.0);
                            hBox.prefHeight(38.0);
                            hBox.prefWidth(38.0);
                            hBox.minHeight(USE_COMPUTED_SIZE);
                            hBox.minWidth(USE_COMPUTED_SIZE);
                            hBox.maxHeight(USE_PREF_SIZE);
                            hBox.maxWidth(USE_PREF_SIZE);
                            hBox.setPadding(new Insets(5, 5, 5, 0));
                            hBox.setStyle("-fx-background-color: transparent;");

                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                            deleteIcon.setSize("15px");
                            deleteIcon.setStyle("-fx-fill: white");
                            deleteButton.setGraphic(deleteIcon);
                            deleteButton.setStyle("-fx-background-color: #920000");
                            deleteButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            deleteButton.setAlignment(Pos.CENTER);

                            FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                            updateIcon.setSize("15px");
                            updateIcon.setStyle("-fx-fill: white");
                            updateButton.setGraphic(updateIcon);
                            updateButton.setStyle("-fx-background-color:  #330033");
                            updateButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            updateButton.setAlignment(Pos.CENTER);

                            hBox.getChildren().add(updateButton);
                            hBox.getChildren().add(deleteButton);

                            deleteButton.setOnAction(event -> {
                                //TODO delete country.
                                Country country = getTableView().getItems().get(getIndex());
                                countries.remove(country);
                                countryService.delete(country.getId());
                            });

                            updateButton.setOnAction(event -> {
                                //TODO Update country
                                Integer id = getTableView().getItems().get(getIndex()).getId();
                                Country country = getTableView().getItems().get(getIndex());
                                countryService.update(id, country);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(hBox);
                            }
                        }
                    };
                }
            };
        actions_column.setCellFactory(columnActionFactory);
//        countries_table.setItems(countries);
    }

    @FXML
    private void onAddNewCountry() throws IOException {
        StackPane addCountry = FXMLLoader.load(getClass().getResource("/templates/add_country.fxml"));
        root_book.getChildren().setAll(addCountry);
        root_stackpane = root_book;
    }


    private ObservableList<Country> getCountries() {
        return FXCollections.observableArrayList(countryService.findAll());
    }
}
