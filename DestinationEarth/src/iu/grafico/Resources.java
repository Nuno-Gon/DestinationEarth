package iu.grafico;

import java.net.URL;

public class Resources {

    public static final URL getResourceFile(String name) {
        URL url = Resources.class.getResource(name);
        return url;
    }
}
