import org.flywaydb.core.Flyway;

public class DatabaseMigration {
    public static void main(String args[]) throws ClassNotFoundException {
        Flyway flyway = new Flyway();
        Class.forName("com.mysql.jdbc.Driver");
        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String name = System.getenv("DB_NAME");
        String url = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&" +
                "useUnicode=true&characterEncoding=utf8", host, port, name);
        flyway.setDataSource(url, user, password);
        flyway.migrate();
    }
}
