package esi.sisad.seman.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 122345L;

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty capital = new SimpleStringProperty();
    private final IntegerProperty population = new SimpleIntegerProperty();

    public Country() {
    }

    public Country(Integer id,
                   String name,
                   String capital,
                   Integer population) {
        this.id.set(id);
        this.name.set(name);
        this.capital.set(capital);
        this.population.set(population);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getCapital() {
        return capital.get();
    }

    public StringProperty capitalProperty() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital.set(capital);
    }

    public Integer getPopulation() {
        return population.get();
    }

    public IntegerProperty populationProperty() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population.set(population);
    }

    @Override
    public String toString() {
        return "Country{" +
               "id=" + id +
               ", name=" + name +
               ", capital=" + capital +
               ", population=" + population +
               '}';
    }
}
