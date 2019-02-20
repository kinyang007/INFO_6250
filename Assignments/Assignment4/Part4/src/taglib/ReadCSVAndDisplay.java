package taglib;

import java.sql.*;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ReadCSVAndDisplay extends SimpleTagSupport {
    private static final String CSV_JDBC_DRIVER = "org.relique.jdbc.csv.CsvDriver";
    private static final String CSV_JDBC_HEADER = "jdbc:relique:csv:";

    private String filename;

    private String[] titles;
    private List<String[]> result;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            if (filename.equals("SalesOrder")) {
                String path = getClass().getResource("../../../files").toString();
                System.out.println(path.substring(5));
                try {
                    Class.forName(CSV_JDBC_DRIVER);
                    Connection connection = DriverManager.getConnection(CSV_JDBC_HEADER + path.substring(5));
                    Statement statement = connection.createStatement();
                    ResultSet results = statement.executeQuery("select * from " + filename);
                    ResultSetMetaData data = results.getMetaData();
                    // get titles
                    int columnCount = data.getColumnCount();
                    titles = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        String title = data.getColumnName(i);
                        titles[i-1] = title;
                    }
                    // get results
                    result = new ArrayList<>();
                    while (results.next()) {
                        String[] row = new String[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            String value = results.getString(i);
                            row[i-1] = value;
                        }
                        result.add(row);
                    }
                    results.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                out.println("<h1 align=\"center\">" + filename + ".csv</h1>");
                out.println("<table border=1>");
                out.println("<tr/>");
                for (int i = 0; i < titles.length; i++) {
                    out.println("<th/>" + titles[i]);
                }
                for (int i = 0; i < result.size(); i++) {
                    out.println("<tr/>");
                    for (int j = 0; j < result.get(i).length; j++) {
                        out.println("<td/>" + result.get(i)[j]);
                    }
                }
                out.println("</table>");
            } else {
                out.println("<p>File name not found!</p>");
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in CSVOperatorTag tag", ex);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
