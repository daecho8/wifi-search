package db;

public class DbTestMain {
    public static void main(String[] args) {
        String number = "11111";
        String name = "qwer";
//        String year = "2023";
//        String workDate = "2023-22-22";

        WifiAll wifi = new WifiAll();;
//        wifi.setWifiNumber(number);
//        wifi.setWifiName(name);
//        wifi.setWifiSetYear(year);
//        wifi.setWifiWorkDate(workDate);

        DbTest dbTest = new DbTest();
//        dbTest.dbSelect();

        dbTest.dbInsert(wifi);
//        dbTest.dbUpdate();
//        dbTest.dbDelete();
    }
}
