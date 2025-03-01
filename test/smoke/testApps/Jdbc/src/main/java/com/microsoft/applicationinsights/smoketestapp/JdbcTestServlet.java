package com.microsoft.applicationinsights.smoketestapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import org.hsqldb.jdbc.JDBCDriver;

@WebServlet("/*")
public class JdbcTestServlet extends HttpServlet {

    public void init() throws ServletException {
        try {
            setupHsqldb();
            if (!Strings.isNullOrEmpty(System.getenv("MYSQL"))) setupMysql();
            if (!Strings.isNullOrEmpty(System.getenv("POSTGRES"))) setupPostgres();
            if (!Strings.isNullOrEmpty(System.getenv("SQLSERVER"))) setupSqlServer();
            // setupOracle();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            doGetInternal(req);
            resp.getWriter().println("ok");
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void doGetInternal(HttpServletRequest req) throws Exception {
        String pathInfo = req.getPathInfo();
        if (pathInfo.equals("/hsqldbPreparedStatement")) {
            hsqldbPreparedStatement();
        } else if (pathInfo.equals("/hsqldbStatement")) {
            hsqldbStatement();
        } else if (pathInfo.equals("/hsqldbBatchPreparedStatement")) {
            hsqldbBatchPreparedStatement();
        } else if (pathInfo.equals("/hsqldbBatchStatement")) {
            hsqldbBatchStatement();
        } else if (pathInfo.equals("/mysqlPreparedStatement")) {
            mysqlPreparedStatement();
        } else if (pathInfo.equals("/mysqlStatement")) {
            mysqlStatement();
        } else if (pathInfo.equals("/postgresPreparedStatement")) {
            postgresPreparedStatement();
        } else if (pathInfo.equals("/postgresStatement")) {
            postgresStatement();
        } else if (pathInfo.equals("/sqlServerPreparedStatement")) {
            sqlServerPreparedStatement();
        } else if (pathInfo.equals("/sqlServerStatement")) {
            sqlServerStatement();
        } else if (pathInfo.equals("/oraclePreparedStatement")) {
            oraclePreparedStatement();
        } else if (pathInfo.equals("/oracleStatement")) {
            oracleStatement();
        } else if (!pathInfo.equals("/")) {
            throw new ServletException("Unexpected url: " + pathInfo);
        }
    }

    private void hsqldbPreparedStatement() throws Exception {
        Connection connection = getHsqldbConnection();
        executePreparedStatement(connection);
        connection.close();
    }

    private void hsqldbStatement() throws Exception {
        Connection connection = getHsqldbConnection();
        executeStatement(connection);
        connection.close();
    }

    private void hsqldbBatchPreparedStatement() throws Exception {
        Connection connection = getHsqldbConnection();
        executeBatchPreparedStatement(connection);
        connection.close();
    }

    private void hsqldbBatchStatement() throws Exception {
        Connection connection = getHsqldbConnection();
        executeBatchStatement(connection);
        connection.close();
    }

    private void mysqlPreparedStatement() throws Exception {
        Connection connection = getMysqlConnection();
        executePreparedStatement(connection);
        connection.close();
    }

    private void mysqlStatement() throws Exception {
        Connection connection = getMysqlConnection();
        executeStatement(connection);
        connection.close();
    }

    private void postgresPreparedStatement() throws Exception {
        Connection connection = getPostgresConnection();
        executePreparedStatement(connection);
        connection.close();
    }

    private void postgresStatement() throws Exception {
        Connection connection = getPostgresConnection();
        executeStatement(connection);
        connection.close();
    }

    private void sqlServerPreparedStatement() throws Exception {
        Connection connection = getSqlServerConnection();
        executePreparedStatement(connection);
        connection.close();
    }

    private void sqlServerStatement() throws Exception {
        Connection connection = getSqlServerConnection();
        executeStatement(connection);
        connection.close();
    }

    private void oraclePreparedStatement() throws Exception {
        Connection connection = getOracleConnection();
        executePreparedStatement(connection);
        connection.close();
    }

    private void oracleStatement() throws Exception {
        Connection connection = getOracleConnection();
        executeStatement(connection);
        connection.close();
    }

    private static void executePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from abc where xyz = ?");
        ps.setString(1, "y");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        }
        rs.close();
        ps.close();
    }

    private void executeStatement(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from abc");
        while (rs.next()) {
        }
        rs.close();
        statement.close();
    }

    private static void executeBatchPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into abc (xyz) values (?)");
        ps.setString(1, "q");
        ps.addBatch();
        ps.setString(1, "r");
        ps.addBatch();
        ps.setString(1, "s");
        ps.addBatch();
        ps.executeBatch();
        ps.close();
    }

    private void executeBatchStatement(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch("insert into abc (xyz) values ('t')");
        statement.addBatch("insert into abc (xyz) values ('u')");
        statement.addBatch("insert into abc (xyz) values ('v')");
        statement.executeBatch();
        statement.close();
    }

    private static void setupHsqldb() throws SQLException {
        Connection connection = getHsqldbConnection();
        setup(connection);
        connection.close();
    }

    private static void setupMysql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = getMysqlConnection();
        setup(connection);
        connection.close();
    }

    private static void setupPostgres() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = getPostgresConnection();
        setup(connection);
        connection.close();
    }

    private static void setupSqlServer() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = getSqlServerConnection();
        setup(connection);
        connection.close();
    }

    private static void setupOracle() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = getOracleConnection();
        setup(connection);
        connection.close();
    }

    private static Connection getHsqldbConnection() throws SQLException {
        return JDBCDriver.getConnection("jdbc:hsqldb:mem:test", null);
    }

    private static Connection getMysqlConnection() throws SQLException {
        String hostname = System.getenv("MYSQL");
        return DriverManager.getConnection("jdbc:mysql://" + hostname + "/mysql", "root", "password");
    }

    private static Connection getPostgresConnection() throws SQLException {
        String hostname = System.getenv("POSTGRES");
        return DriverManager.getConnection("jdbc:postgresql://" + hostname + "/postgres", "postgres", "");
    }

    private static Connection getSqlServerConnection() throws SQLException {
        String hostname = System.getenv("SQLSERVER");
        return DriverManager.getConnection("jdbc:sqlserver://" + hostname, "sa", "Password1");
    }

    private static Connection getOracleConnection() throws SQLException {
        String hostname = System.getenv("ORACLE");
        return DriverManager.getConnection("jdbc:oracle:thin:@" + hostname, "system", "password");
    }

    private static void setup(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.execute("create table abc (xyz varchar(10))");
            statement.execute("insert into abc (xyz) values ('x')");
            statement.execute("insert into abc (xyz) values ('y')");
            statement.execute("insert into abc (xyz) values ('z')");
        } finally {
            statement.close();
        }
    }
}
