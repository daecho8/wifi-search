package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbTest {
    public List<WifiAll> dbSelect(String X, String Y) {
    	
    	List<WifiAll> wifiList = new ArrayList<>();
    	
        String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

//            String sql = "SELECT * " +
//                    " from WIFIALL LIMIT 20;";
            
            String sql = "SELECT\n"
            		+ "    (\n"
            		+ "       6371 * acos ( cos ( radians( ? ) )\n"
            		+ "          * cos( radians( LAT ) )\n"
            		+ "          * cos( radians( LNT) - radians( ? ) )\n"
            		+ "          + sin ( radians( ? ) ) * sin( radians( LAT ) )\n"
            		+ "       )\n"
            		+ "   ) AS Distance, WIFIALL.*\n"
            		+ "FROM WIFIALL\n"
            		+ "ORDER BY Distance\n"
            		+ "LIMIT 20";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, X); // use ? in sql
            preparedStatement.setString(2, Y);
            preparedStatement.setString(3, X);
            
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                String number = rs.getString("X_SWIFI_MGR_NO");
                String Distance = rs.getString("Distance");
                
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");	
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");	
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");
            	
                WifiAll wifi = new WifiAll();
//                wifi.setWifiNumber(number);
//                wifi.setWifiName(name);
                wifi.setDistance(Distance);
                
                wifi.setMGR_NO(X_SWIFI_MGR_NO);
                wifi.setWRDOFC(X_SWIFI_WRDOFC);
                wifi.setMAIN_NM(X_SWIFI_MAIN_NM);
                wifi.setADRES1(X_SWIFI_ADRES1);
                wifi.setADRES2(X_SWIFI_ADRES2);
                wifi.setINSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                wifi.setINSTL_TY(X_SWIFI_INSTL_TY);
                wifi.setINSTL_MBY(X_SWIFI_INSTL_MBY);
                wifi.setSVC_SE(X_SWIFI_SVC_SE);
                wifi.setCMCWR(X_SWIFI_CMCWR);
                wifi.setCNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                wifi.setINOUT_DOOR(X_SWIFI_INOUT_DOOR);
                wifi.setREMARS3(X_SWIFI_REMARS3);
                wifi.setLAT(LAT);
                wifi.setLNT(LNT);
                wifi.setWORK_DTTM(WORK_DTTM);
             
                wifiList.add(wifi);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return wifiList;
    }
    
    public void searchLog(String LAT, String LNT) {

        String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "INSERT into searchLog(LAT, LNT, onDate) "
            		+ "values (?, ?, CURRENT_TIMESTAMP());\n";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, LAT);
            preparedStatement.setString(2, LNT);



            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("saved");
            } else {
                System.out.println("failed to save");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

	public List<LogAll> showLog() {
	
	List<LogAll> logList = new ArrayList<>();
	
    String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
    String dbUserId = "testuser1";
    String dbPassword = "zerobase";
    try {
        Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    try {
        connection = DriverManager.getConnection(url, dbUserId, dbPassword);

//        String nnn = "name";
        String sql = "SELECT * " +
                " from searchLog "
                + "ORDER BY ID DESC";
        preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, nnn); // use ? in sql

        rs = preparedStatement.executeQuery();
        while (rs.next()) {
        	int ID = rs.getInt("ID");
            String LAT = rs.getString("LAT");
            String LNT = rs.getString("LNT");
            String onDate = rs.getString("onDate");

            LogAll log = new LogAll();
            log.setID(ID);
            log.setLAT(LAT);
            log.setLNT(LNT);
            log.setTIMESTAMP(onDate);
            
            logList.add(log);
        }


    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {

        try {
            if (rs != null && rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    return logList;
}

    public void dbInsert(WifiAll wifi) {

        String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            
            String sql = " INSERT into WIFIALL("
            		+ "X_SWIFI_MGR_NO,"	//관리번호
            		+ "X_SWIFI_WRDOFC,"	//자치구
            		+ "X_SWIFI_MAIN_NM,"	//와이파이명
            		+ "X_SWIFI_ADRES1,"	//도로명주소
            		+ "X_SWIFI_ADRES2,"	//상세주소
            		+ "X_SWIFI_INSTL_FLOOR,"	//설치위치(층)
            		+ "X_SWIFI_INSTL_TY,"	//설치유형
            		+ "X_SWIFI_INSTL_MBY,"	//설치기관
            		+ "X_SWIFI_SVC_SE,"	//서비스구분
            		+ "	X_SWIFI_CMCWR,"	//망종류
            		+ "	X_SWIFI_CNSTC_YEAR,"	//설치년도
            		+ "	X_SWIFI_INOUT_DOOR,"	//실내외구분
            		+ "	X_SWIFI_REMARS3,"	//wifi접속환경
            		+ "	LAT,"	//Y좌표
            		+ "	LNT,"	//X좌표
            		+ "	WORK_DTTM)" //작업일자
            		+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifi.getMGR_NO());
            preparedStatement.setString(2, wifi.getWRDOFC());
            preparedStatement.setString(3, wifi.getMAIN_NM());
            preparedStatement.setString(4, wifi.getADRES1());
            preparedStatement.setString(5, wifi.getADRES2());
            preparedStatement.setString(6, wifi.getINSTL_FLOOR());
            preparedStatement.setString(7, wifi.getINSTL_TY());
            preparedStatement.setString(8, wifi.getINSTL_MBY());
            preparedStatement.setString(9, wifi.getSVC_SE());
            preparedStatement.setString(10, wifi.getCMCWR());
            preparedStatement.setString(11, wifi.getCNSTC_YEAR());
            preparedStatement.setString(12, wifi.getINOUT_DOOR());
            preparedStatement.setString(13, wifi.getREMARS3());
            preparedStatement.setString(14, wifi.getLAT());
            preparedStatement.setString(15, wifi.getLNT());
            preparedStatement.setString(16, wifi.getWORK_DTTM());


            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
//                System.out.println("saved");
            } else {
                System.out.println("failed to save");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void dbUpdate() {

        String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String nameValue = "ccc";
            String ageValue = "123";

            String sql = "UPDATE member " +
                    "SET age = ? " +
                    "WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ageValue);
            preparedStatement.setString(2, nameValue);


            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("Updated");
            } else {
                System.out.println("failed to Update");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void dbDelete() {

        String url = "jdbc:mariadb://192.168.1.134:3306/testdb1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String nameValue = "bbb";

            String sql = "DELETE FROM `member` " +
                    "WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameValue);


            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("Deleted");
            } else {
                System.out.println("failed to Delete");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
