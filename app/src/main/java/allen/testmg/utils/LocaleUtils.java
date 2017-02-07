package allen.testmg.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Allen on 24-Jan-17.
 */

public class LocaleUtils {
    public static final boolean isIndoPhone(Context context) {
        String countryID = "id@idn";  //"id@idn";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String simCountry = tm.getSimCountryIso();
        int phoneType = tm.getPhoneType();
        String phoneCountry = null;
        if (phoneType != TelephonyManager.PHONE_TYPE_CDMA)
            phoneCountry = tm.getNetworkCountryIso();
        String locCountry = context.getResources().getConfiguration().locale.getCountry().toLowerCase();
        Log.d("country", "simCountry=" + simCountry);
        Log.d("country", "phoneCountry=" + phoneCountry);
        Log.d("country", "locCountry=" + locCountry);
        if (simCountry != null && simCountry.length() > 0 && countryID.contains(simCountry.toLowerCase()))
            return true;
        if (phoneCountry != null && phoneCountry.length() > 0 && countryID.contains(phoneCountry.toLowerCase()))
            return true;
        return false;
    }
}
