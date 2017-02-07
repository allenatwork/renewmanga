package allen.testmg.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Allen on 04-Jan-17.
 */

public class ListUtils {
    public static boolean isNullOrEmpty(final Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty(final Map<?, ?> m) {
        return m == null || m.isEmpty();
    }
}
