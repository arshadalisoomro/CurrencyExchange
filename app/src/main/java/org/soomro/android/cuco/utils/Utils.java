package org.soomro.android.cuco.utils;

import android.content.Context;
import android.graphics.Typeface;

import org.soomro.android.cuco.model.Meta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Arshay on 8/5/2017.
 */

public class Utils {

    public static Typeface getFontFace(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/proxima-nova.otf");
    }

    public static List<Meta> getMetaList(){
        List<Meta> metaList = new ArrayList<>();
        metaList.add(new Meta("Arab Emirates Dirham", "AED", "د.إ"));
        metaList.add(new Meta("Afghan Afghani", "AFN", "؋"));
        metaList.add(new Meta("Albanian Lek","ALL", "Lek"));
        metaList.add(new Meta("Armenian Dram", "AMD", "֏"));
        metaList.add(new Meta("NL Antillean Guilder", "ANG", "ƒ"));
        metaList.add(new Meta("Angolan Kwanza","AOA", "Kz"));
        metaList.add(new Meta("Argentine Peso", "ARS", "$"));
        metaList.add(new Meta("Australian Dollar", "AUD","A$"));
        metaList.add(new Meta("Aruban or Dutch Guilder", "AWG", "ƒ"));
        metaList.add(new Meta("Azerbaijani Manat", "AZN", "\u20BC"));
        metaList.add(new Meta("Brazilian Real", "BRL", "R$"));
        metaList.add(new Meta("Bahamian Dollar","BSD", "B$"));
        metaList.add(new Meta("Canadian Dollar", "CAD", "$"));
        metaList.add(new Meta("Swiss Franc", "CHF", "CHF"));
        metaList.add(new Meta("Chilean Peso", "CLP", "$"));
        metaList.add(new Meta("Chinese Yuan Renminbi", "CNY", "¥"));
        metaList.add(new Meta("Colombian Peso", "COP", "$"));
        metaList.add(new Meta("Czech Koruna","CZK", "Kč"));
        metaList.add(new Meta("Danish Krone", "DKK", "kr"));
        metaList.add(new Meta("Euro", "EUR","€"));
        metaList.add(new Meta("Fiji Dollar", "FJD", "FJ$"));
        metaList.add(new Meta("British Pound", "GBP", "£"));
        metaList.add(new Meta("Ghanaian New Cedi", "GHS", "GH₵"));
        metaList.add(new Meta("Guatemalan Quetzal", "GTO", "Q"));
        metaList.add(new Meta("Hong Kong Dollar", "HKD", "$"));
        metaList.add(new Meta("Honduran Lempira", "HNL", "L"));
        metaList.add(new Meta("Croatian Kuna","HRK", "kn"));
        metaList.add(new Meta("Hungarian Forint", "HUF", "Ft"));
        metaList.add(new Meta("Indonesian Rupiah", "IDR", "Rp"));
        metaList.add(new Meta("Israeli Shekel", "ILS", "₪"));
        metaList.add(new Meta("Indian Rupee", "INR", "₹"));
        metaList.add(new Meta("Iceland Krona", "ISK", "kr"));
        metaList.add(new Meta("Jamaican Dollar", "JMD", "J$"));
        metaList.add(new Meta("Japanese Yen", "JPY", "¥"));
        metaList.add(new Meta("South-Korean Won", "KRW", "₩"));
        metaList.add(new Meta("Sri Lanka Rupee", "LKR", "₨"));
        metaList.add(new Meta("Moroccan Dirham","MAD", ".د.م"));
        metaList.add(new Meta("Myanmar Kyat", "MMK", "K"));
        metaList.add(new Meta("Mexican Peso","MXN", "$"));
        metaList.add(new Meta("Malaysian Ringgit","MYR", "RM"));
        metaList.add(new Meta("Norwegian Kroner", "NOK", "kr"));
        metaList.add(new Meta("New Zealand Dollar", "NZD", "$"));
        metaList.add(new Meta("Panamanian Balboa","PAB", "B/."));
        metaList.add(new Meta("Peruvian Nuevo Sol","PEN", "S/."));
        metaList.add(new Meta("Philippine Peso", "PHP", "₱"));
        metaList.add(new Meta("Pakistan Rupee", "PKR", "₨"));
        metaList.add(new Meta("Polish Zloty", "PLN", "zł"));
        metaList.add(new Meta("Romanian New Lei", "RON", "lei"));
        metaList.add(new Meta("Serbian Dinar", "RSD", "РСД"));
        metaList.add(new Meta("Russian Rouble", "RUB", "руб"));
        metaList.add(new Meta("Swedish Krona", "SEK", "kr"));
        metaList.add(new Meta("Singapore Dollar", "SGD", "S$"));
        metaList.add(new Meta("South African Rand","ZAR", "R"));
        metaList.add(new Meta("Thai Baht","THB", "฿"));
        metaList.add(new Meta("Tunisian Dinar", "TND", "DT"));
        metaList.add(new Meta("Turkish Lira","TRY", "TL"));
        metaList.add(new Meta("Trinidad/Tobago Dollar", "TTD", "$"));
        metaList.add(new Meta("Taiwan Dollar", "TWD", "NT$"));
        metaList.add(new Meta("US Dollar","USD", "$"));
        metaList.add(new Meta("Venezuelan Bolivar Fuerte","VEF", "Bs"));
        metaList.add(new Meta("Vietnamese Dong", "VND", "₫"));
        metaList.add(new Meta("CFA Franc BEAC","XAF", "FCFA"));
        metaList.add(new Meta("East Caribbean Dollar", "XCD", "$"));
        metaList.add(new Meta("CFP Franc","XPF", "F"));

        return metaList;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Meta getMeta(String abbr) {
        Meta meta;

        switch (abbr) {
            case "AED" : meta = new Meta("UAE Dirham", "د.إ"); break;
            case "AFN" : meta = new Meta("Afghan Afghani", "؋"); break;
            case "ALL" : meta = new Meta("Albanian Lek", "Lek");break;
            case "AMD" : meta = new Meta("Armenian Dram", "֏");break;
            case "ANG" : meta = new Meta("NL Antillian Guilder", "ƒ");break;
            case "AOA" : meta = new Meta("Angolan Kwanza", "Kz");break;
            case "ARS" : meta = new Meta("Argentine Peso", "$");break;
            case "AUD" : meta = new Meta("Australian Dollar","A$");break;
            case "AWG" : meta = new Meta("Aruban or Dutch Guilder", "ƒ");break;
            case "AZN" : meta = new Meta("Azerbaijani Manat", "\u20BC");break;
            case "BRL" : meta = new Meta("Brazilian Real", "R$");break;
            case "BSD" : meta = new Meta("Bahamian Dollar", "B$");break;
            case "CAD" : meta = new Meta("Canadian Dollar", "$");break;
            case "CHF" : meta = new Meta("Swiss Franc", "CHF");break;
            case "CLP" : meta = new Meta("Chilean Peso", "$");break;
            case "CNY" : meta = new Meta("Chinese Yuan Renminbi", "¥");break;
            case "COP" : meta = new Meta("Colombian Peso", "$");break;
            case "CZK" : meta = new Meta("Czech Koruna", "Kč");break;
            case "DKK" : meta = new Meta("Danish Krone", "kr");break;
            case "EUR" : meta = new Meta("Euro","€");break;
            case "FJD" : meta = new Meta("Fiji Dollar", "FJ$");break;
            case "GBP" : meta = new Meta("British Pound", "£");break;
            case "GHS" : meta = new Meta("Ghanaian New Cedi", "GH₵");break;
            case "GTQ" : meta = new Meta("Guatemalan Quetzal", "Q");break;
            case "HKD" : meta = new Meta("Hong Kong Dollar", "$");break;
            case "HNL" : meta = new Meta("Honduran Lempira", "L");break;
            case "HRK" : meta = new Meta("Croatian Kuna", "kn");break;
            case "HUF" : meta = new Meta("Hungarian Forint", "Ft");break;
            case "IDR" : meta = new Meta("Indonesian Rupiah", "Rp");break;
            case "ILS" : meta = new Meta("Israeli New Shekel", "₪");break;
            case "INR" : meta = new Meta("Indian Rupee", "₹");break;
            case "ISK" : meta = new Meta("Iceland Krona", "kr");break;
            case "JMD" : meta = new Meta("Jamaican Dollar", "J$");break;
            case "JPY" : meta = new Meta("Japanese Yen", "¥");break;
            case "KRW" : meta = new Meta("South-Korean Won", "₩");break;
            case "LKR" : meta = new Meta("Sri Lanka Rupee", "₨");break;
            case "MAD" : meta = new Meta("Moroccan Dirham", ".د.م");break;
            case "MMK" : meta = new Meta("Myanmar Kyat", "K");break;
            case "MXN" : meta = new Meta("Mexican Peso", "$");break;
            case "MYR" : meta = new Meta("Malaysian Ringgit", "RM");break;
            case "NOK" : meta = new Meta("Norwegian Kroner", "kr");break;
            case "NZD" : meta = new Meta("New Zealand Dollar", "$");break;
            case "PAB" : meta = new Meta("Panamanian Balboa", "B/.");break;
            case "PEN" : meta = new Meta("Peruvian Nuevo Sol", "S/.");break;
            case "PHP" : meta = new Meta("Philippine Peso", "₱");break;
            case "PKR" : meta = new Meta("Pakistan Rupee", "₨");break;
            case "PLN" : meta = new Meta("Polish Zloty", "zł");break;
            case "RON" : meta = new Meta("Romanian New Lei", "lei");break;
            case "RSD" : meta = new Meta("Serbian Dinar", "РСД");break;
            case "RUB" : meta = new Meta("Russian Rouble", "руб");break;
            case "SEK" : meta = new Meta("Swedish Krona", "kr");break;
            case "SGD" : meta = new Meta("Singapore Dollar", "S$");break;
            case "THB" : meta = new Meta("Thai Baht", "฿");break;
            case "TND" : meta = new Meta("Tunisian Dinar", "DT");break;
            case "TRY" : meta = new Meta("Turkish Lira", "TL");break;
            case "TTD" : meta = new Meta("Trinidad/Tobago Dollar", "$");break;
            case "TWD" : meta = new Meta("Taiwan Dollar", "NT$");break;
            case "USD" : meta = new Meta("US Dollar", "$");break;
            case "VEF" : meta = new Meta("Venezuelan Bolivar Fuerte", "Bs");break;
            case "VND" : meta = new Meta("Vietnamese Dong", "₫");break;
            case "XAF" : meta = new Meta("CFA Franc BEAC", "FCFA");break;
            case "XCD" : meta = new Meta("East Caribbean Dollar", "$");break;
            case "XPF" : meta = new Meta("CFP Franc", "F");break;
            case "ZAR" : meta = new Meta("South African Rand", "R");break;
            default : meta = new Meta("Not found", "NaN");break;
        }

        return meta;
    }

}
