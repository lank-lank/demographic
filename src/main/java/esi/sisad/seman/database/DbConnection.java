package esi.sisad.seman.database;

import lombok.Getter;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class DbConnection implements AutoCloseable {
    private static DbConnection instance;
    private static final String username = "neo4j";
    private static final String password = "semantic";
    private static final String uri = "bolt://localhost:7687";

    @Getter
    private final Driver driver;

    private DbConnection() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
}
