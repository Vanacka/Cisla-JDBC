public class NumberToWords {

    private static final String[] jednotkyCZ = {
            "nula", "jedna", "dva", "tři", "čtyři", "pět", "šest", "sedm", "osm", "devět"
    };

    private static final String[] nactkyCZ = {
            "deset", "jedenáct", "dvanáct", "třináct", "čtrnáct", "patnáct", "šestnáct", "sedmnáct", "osmnáct", "devatenáct"
    };

    private static final String[] desitkyCZ = {
            "", "", "dvacet", "třicet", "čtyřicet", "padesát", "šedesát", "sedmdesát", "osmdesát", "devadesát"
    };

    private static final String[] stovkyCZ = {
            "", "sto", "dvě stě", "tři sta", "čtyři sta", "pět set", "šest set", "sedm set", "osm set", "devět set"
    };
    private static final String[] tisicovkyCZ = {
            "", "tisíc", "dva tisíce", "tři tisíce", "čtyři tisíce", "pět tisíc", "šest tisíc", "sedm tisíc", "osm tisíc", "devět tisíc", "deset tisíc"
    };

    private static final String[] jednotkySK = {
            "nula", "jeden", "dva", "tri", "štyri", "päť", "šesť", "sedem", "osem", "deväť"
    };

    private static final String[] nactkySK = {
            "desať", "jedenásť", "dvanásť", "trinásť", "štrnásť", "pätnásť", "šestnásť", "sedemnásť", "osemnásť", "devätnásť"
    };

    private static final String[] desitkySK = {
            "", "", "dvadsať", "tridsať", "štyridsať", "päťdesiat", "šesťdesiat", "sedemdesiat", "osemdesiat", "deväťdesiat"
    };

    private static final String[] stovkySK = {
            "", "sto", "dvesto", "tristo", "štyristo", "päťsto", "šesťsto", "sedemsto", "osemsto", "deväťsto"
    };

    private static final String[] tisicovkySK = {
            "", "tisíc", "dva tisíce", "tri tisíce", "štyri tisíce", "päť tisíc", "šesť tisíc", "sedem tisíc", "osem tisíc", "deväť tisíc", "desať tisíc"
    };


    public static String convertCzech(int number) {
        int kolikJednotek = 0;
        StringBuilder prevedeneCislo = new StringBuilder();
        if (number == 0) {
            prevedeneCislo = new StringBuilder(jednotkyCZ[0]);
        }
        else {
            while (number != 0) {
                if (number >= 1000) {
                    kolikJednotek = number / 1000;
                    prevedeneCislo = new StringBuilder(tisicovkyCZ[kolikJednotek]);
                    number = number % 1000;
                } else if (number >= 100) {
                    kolikJednotek = number / 100;
                    prevedeneCislo.append(" ").append(stovkyCZ[kolikJednotek]);
                    number = number % 100;
                } else if (number >= 20) {
                    kolikJednotek = number / 10;
                    prevedeneCislo.append(" ").append(desitkyCZ[kolikJednotek]);
                    number = number % 10;
                } else if (number >= 10) {
                    kolikJednotek = number % 10;
                    prevedeneCislo.append(" ").append(nactkyCZ[kolikJednotek]);
                    number = 0;
                } else if (number >= 1) {
                    kolikJednotek = number;
                    prevedeneCislo.append(" ").append(jednotkyCZ[kolikJednotek]);
                    number = 0;
                }
            }
        }
        return prevedeneCislo.toString();
    }
    public static String convertSlovak(int number) {
        int kolikJednotek = 0;
        StringBuilder prevedeneCislo = new StringBuilder();
        if (number == 0) {
            prevedeneCislo = new StringBuilder(jednotkySK[0]);
        }
        else {
            while (number != 0) {
                if (number >= 1000) {
                    kolikJednotek = number / 1000;
                    prevedeneCislo = new StringBuilder(tisicovkySK[kolikJednotek]);
                    number = number % 1000;
                } else if (number >= 100) {
                    kolikJednotek = number / 100;
                    prevedeneCislo.append(" ").append(stovkySK[kolikJednotek]);
                    number = number % 100;
                } else if (number >= 20) {
                    kolikJednotek = number / 10;
                    prevedeneCislo.append(" ").append(desitkySK[kolikJednotek]);
                    number = number % 10;
                } else if (number >= 10) {
                    kolikJednotek = number % 10;
                    prevedeneCislo.append(" ").append(nactkySK[kolikJednotek]);
                    number = 0;
                } else if (number >= 1) {
                    kolikJednotek = number;
                    prevedeneCislo.append(" ").append(jednotkySK[kolikJednotek]);
                    number = 0;
                }
            }
        }
        return prevedeneCislo.toString();
    }
}