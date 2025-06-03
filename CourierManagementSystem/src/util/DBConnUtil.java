package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnUtil {

    public static String getConnectionString(String fileName) throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        props.load(fis);

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String protocol = props.getProperty("protocol");
        String system = props.getProperty("system");
        String port = props.getProperty("port");
        String database = props.getProperty("database");

        return protocol + "//" + system + ":" + port + "/" + database + "?user=" + user + "&password=" + password;
    }
}
