package lab.helper;

/**
 *
 * @author tzuyichao
 */
public final class Fuseki2Utils {
    public static String makeRegistryKey(String name) {
        if(name.startsWith("/")) {
            return name;
        } else {
            return String.format("/%s", name);
        }
    }
}
