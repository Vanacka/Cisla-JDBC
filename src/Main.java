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
            /*// Vytvori tabulku
            PreparedStatement vytvor = conn.prepareStatement("CREATE TABLE cisla (\n" +
                    "    cislo INT PRIMARY KEY,\n" +
                    "    cislo_cz VARCHAR NOT NULL,\n" +
                    "    cislo_sk VARCHAR NOT NULL\n" +
                    ")");*/
            PreparedStatement jeDB = conn.prepareStatement("SELECT * FROM cisla");
            ResultSet rs = jeDB.executeQuery();
            // Vymaze stavajici data v tabulce
            if(rs.next()) {
                PreparedStatement vymaz = conn.prepareStatement("DELETE FROM cisla");
                vymaz.executeUpdate();
            }

            // Zapise nova data do tabulky od 0 do 1000
            for (int i = 0; i < 1000; i++) {
                PreparedStatement vloz = conn.prepareStatement("INSERT INTO cisla VALUES (?, ?, ?)");
                String convertedCZ = NumberToWords.convertCzech(i);
                String convertedSK = NumberToWords.convertSlovak(i);
                vloz.setInt(1, i);
                vloz.setString(2, convertedCZ);
                vloz.setString(3, convertedSK);
                vloz.executeUpdate();
            }
            // Merim cas
            float prumernyCas1 = 0;
            float prumernyCas2 = 0;
            for (int i = 0; i <= 1000; i = i + 97) {
                PreparedStatement zkontroluj1 = conn.prepareStatement("SELECT cislo_cz FROM cislo WHERE cislo=?");
                PreparedStatement zkontroluj2 = conn.prepareStatement("SELECT cislo FROM cislo WHERE cislo_cz=?");
                String convertedCZ = NumberToWords.convertCzech(i);
                long start1 = System.currentTimeMillis();
                zkontroluj1.setInt(1, i);
                long end1 = System.currentTimeMillis();
                prumernyCas1 += end1 - start1;
                long start2 = System.currentTimeMillis();
                zkontroluj2.setString(1, convertedCZ);
                long end2 = System.currentTimeMillis();
                prumernyCas2 += end2 - start2;
            }
            System.out.println("1: " + prumernyCas1 / 10);
            System.out.println("2: " + prumernyCas2 / 10);

            // Zapise nova data do tabulky od 1001 do 10000
            for (int i = 1001; i <= 10000; i++) {
                PreparedStatement vloz = conn.prepareStatement("INSERT INTO cisla VALUES (?, ?, ?)");
                String convertedCZ = NumberToWords.convertCzech(i);
                String convertedSK = NumberToWords.convertSlovak(i);
                vloz.setInt(1, i);
                vloz.setString(2, convertedCZ);
                vloz.setString(3, convertedSK);
                vloz.executeUpdate();
            }
            // Merim cas
            float prumernyCas3 = 0;
            float prumernyCas4 = 0;
            for (int i = 0; i < 1000; i = i + 97) {

                PreparedStatement zkontroluj1 = conn.prepareStatement("SELECT cislo_cz FROM cislo WHERE cislo=?");
                PreparedStatement zkontroluj2 = conn.prepareStatement("SELECT cislo FROM cislo WHERE cislo_cz=?");
                String convertedCZ = NumberToWords.convertCzech(i);
                long start3 = System.currentTimeMillis();
                zkontroluj1.setInt(1, i);
                long end3 = System.currentTimeMillis();
                prumernyCas3 += end3 - start3;
                long start4 = System.currentTimeMillis();
                zkontroluj2.setString(1, convertedCZ);
                long end4 = System.currentTimeMillis();
                prumernyCas4 += end4 - start4;
            }
            System.out.println("3: " + prumernyCas3 / 10);
            System.out.println("4: " + prumernyCas4 / 10);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}