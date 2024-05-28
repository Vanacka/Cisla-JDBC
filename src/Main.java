import pl.allegro.finance.tradukisto.ValueConverters;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, HashSet<Integer>> vypujcky = new HashMap<Integer, HashSet<Integer>>();
        //://localhost:5432/db6145",
        //                "db6145",
        //                "Eith7ahz8Ohxehae5voh"
        //ssh -L 5432:localhost:5432 ivan.merkulov@svs.gyarab.cz
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db6145", "db6145", "Eith7ahz8Ohxehae5voh")) {
            PreparedStatement jeDB = conn.prepareStatement("SELECT * FROM cislo");
            ResultSet rs = jeDB.executeQuery();
            // Vymaze stavajici data v tabulce
            if(rs.next()) {
                PreparedStatement vymaz = conn.prepareStatement("DELETE FROM cislo");
                vymaz.executeUpdate();
            }
            // Zapise nova data do tabulky
            for (int i = 0; i < 1000; i++) {
                PreparedStatement vloz = conn.prepareStatement("INSERT INTO cislo VALUES (?, ?, ?, ?, ?)");
                ValueConverters intCzechConverter = ValueConverters.CZECH_INTEGER;
                ValueConverters intEnglishConverter = ValueConverters.ENGLISH_INTEGER;
                ValueConverters intGermanConverter = ValueConverters.GERMAN_INTEGER;
                ValueConverters intRussianConverter = ValueConverters.RUSSIAN_INTEGER;
                String czechValueAsWords = intCzechConverter.asWords(i);
                String englishValueAsWords = intEnglishConverter.asWords(i);
                String germanValueAsWords = intGermanConverter.asWords(i);
                String russianValueAsWords = intRussianConverter.asWords(i);

                vloz.setInt(1, i);
                vloz.setString(2, String.valueOf(czechValueAsWords));
                vloz.setString(3, String.valueOf(englishValueAsWords));
                vloz.setString(4, String.valueOf(germanValueAsWords));
                vloz.setString(5, String.valueOf(russianValueAsWords));

                vloz.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}