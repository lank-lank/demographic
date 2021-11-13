package esi.sisad.seman.service;

import esi.sisad.seman.database.DbConnection;
import esi.sisad.seman.model.Country;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.util.ArrayList;
import java.util.List;

public class CountryService {
    Session session = null;

    public CountryService() {
        session = DbConnection.getInstance().getDriver().session();
    }

    public void save(Country c) {
        Result result = session.run(PreparedQuery.ADD_COUNTRY,
                                    Values.parameters("name", c.getName(),
                                                      "capital", c.getCapital(),
                                                      "population", c.getPopulation()));
        System.out.println(result.next());
    }

    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        Result result = session.run(PreparedQuery.FIND_ALL_COUNTRIES);
        while (result.hasNext()) {
            Record r = result.next();
            countries.add(new Country(r.get("id(c)", 0),
                                      r.get("c.name", ""),
                                      r.get("c.capital", ""),
                                      r.get("c.population", 0)));
        }
        return countries;
    }

    public void update(Integer id, Country c) {
        Result result = session.run(PreparedQuery.UPDATE_COUNTRY,
                                    Values.parameters("id", id, "name", c.getName(),
                                                      "capital", c.getCapital(),
                                                      "population", c.getPopulation()));
        System.out.println(result);
    }

    public void delete(Integer id) {
        Result result = session.run(PreparedQuery.DELETE_COUNTRY_BY_ID,
                                    Values.parameters("id", id));
        System.out.println(result);
    }
}
