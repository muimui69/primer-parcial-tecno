package config;

public class ConfigDB {
    private String user;
    private String password;
    private String host;
    private String port;
    private String dbName;

    public ConfigDB() {
        this.user = "grupo05sc";
        this.password = "grup005grup005*";
        this.host = "mail.tecnoweb.org.bo";
        this.port = "5432";
        this.dbName = "db_grupo05sc";
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }
}
