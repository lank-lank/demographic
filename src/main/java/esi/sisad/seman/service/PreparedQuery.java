package esi.sisad.seman.service;

public class PreparedQuery {
    public static final String ADD_COUNTRY = "CREATE (c:Country {" +
                                             "name: $name, " +
                                             "capital: $capital, " +
                                             "population: $population}) " +
                                             "RETURN c";
    public static final String FIND_ALL_COUNTRIES = "MATCH (c:Country) RETURN id(c), c.name, c.capital, c.population";
    public static final String UPDATE_COUNTRY = "MATCH (c:Country) WHERE id(c)=$id SET " +
                                                "c.capital = $capital," +
                                                "c.population = $population," +
                                                "c.name = $name " +
                                                "RETURN c";
    public static final String DELETE_COUNTRY_BY_ID = "MATCH (c:Country) WHERE id(c)=$id DELETE c";
}
