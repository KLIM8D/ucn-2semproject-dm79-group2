package db;

/**
 * Created: 12-10-2012
 * @version: 0.1
 * Filename: DataAccess.java
 * Description: A class which handles all the database connections
 *
 * Driver used: http://jtds.sourceforge.net/index.html
 *
 * @changes
 * 19.12 MKS: Changed driver for better performance and thread safety. Changed the class to be thread safe.
 */

import com.sun.rowset.CachedRowSetImpl;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import javax.sql.rowset.CachedRowSet;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DataAccess
{
    private int _dbType = 1;
    private final boolean _enableSqlMonitor = true;
    private BoneCP _connectionPool;
    private static DataAccess _instance;

    public void setDbType(int value)
    { _dbType = value; }

    public int getTotalLeasedConnections()
    {
        return _connectionPool.getTotalLeased();
    }

    protected Connection getCon() throws SQLException
    {
        return _connectionPool.getConnection();
    }

    protected void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static DataAccess getInstance()
    {
        return _instance != null ? _instance : (_instance = new DataAccess());
    }

    private DataAccess()
    {
        switch(_dbType)
        {
            case 1:
                try
                {
                    //MSSQL Server (JDBC Driver)
                    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    //MSSQL Server (JTDS Driver)
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    BoneCPConfig config = new BoneCPConfig();
                    config.setJdbcUrl("jdbc:jtds:sqlserver://IPADDR//DBNAME");
                    config.setUsername("USERNAME");
                    config.setPassword("PASSWORD");
                    config.setMinConnectionsPerPartition(5);
                    config.setMaxConnectionsPerPartition(10);
                    config.setPartitionCount(1);
                    _connectionPool = new BoneCP(config);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;

        }
    }

    /**
     * Get a ResultSet which contains the rows returned from the database. comment
     *
     * @return ResultSet the rows returned from the database
     *
     */
    public ResultSet callCommandGetResultSet(PreparedStatement _sqlCommand, Connection _con)
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        ResultSet newResultSet = null;
        try
        {
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            newResultSet = _sqlCommand.executeQuery();
            cachedRowSet.populate(newResultSet);

            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(newResultSet, _sqlCommand, _con);
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "CallCommandGetResultSet()");
        }

        return null;
    }

    /**
    * Get a ResultSet which contains the rows returned from the database. comment
    * The method doesn't contain benchmark/query execution time.
    *
    * @return ResultSet the rows returned from the database
    *
    */
    private ResultSet callCommandGetResultSetWithOutMonitor(PreparedStatement _sqlCommand, Connection _con)
    {
        ResultSet newResultSet = null;
        try
        {
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            newResultSet = _sqlCommand.executeQuery();
            cachedRowSet.populate(newResultSet);

            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(newResultSet, _sqlCommand, _con);
        }

        return null;
    }

    /**
    * Used for insert, update and delete
    *
    * @return int number of rows affected by the query
    *
    */
    public int callCommand(PreparedStatement _sqlCommand, Connection _con)
    {
        int returnVal = 0;

        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            _sqlCommand.addBatch();
            int[] updateCount = _sqlCommand.executeBatch();

            for (int i = 0; i < updateCount.length; i++)
                returnVal += updateCount[i];
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(null, _sqlCommand, _con);
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommand()");
        }

        return returnVal;
    }

    /**
    * Get a single value from a column
    *
    * @return String the value from the database
    *
    */
    public String callCommandGetField(PreparedStatement _sqlCommand, Connection _con)
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        String returnVal = "";
        ResultSet listData = null;
        try
        {
            listData = callCommandGetResultSetWithOutMonitor(_sqlCommand, _con);
            if (listData != null && listData.next())
            {
                returnVal = listData.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(listData, null, null);
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommandGetField()");
        }

        return returnVal;
    }

    /**
    * Get a single row from the database
    *
    * @return ResultSet which contains the row
    *
    */
    public ResultSet callCommandGetRow(PreparedStatement _sqlCommand, Connection _con)
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        ResultSet listData = null;
        try
        {
            CachedRowSet dataSet = new CachedRowSetImpl();
            listData = callCommandGetResultSetWithOutMonitor(_sqlCommand, _con);

            if (listData != null)
            {
                dataSet.populate(listData);
                return dataSet;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(listData, null, null);
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime - startTime), "callCommandGetField()");
        }

        return null;
    }

    /**
    * Format a Date object to SQL datetime format
    *
    * @param d the date which you want formated
    * @return returns a formated String
    *
    */
    public String dateToSqlDate(java.util.Date d)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(d);
    }

    /**
    * Get SQL current date and time
    *
    * @param sqlDate the sql.Date object which you want time extracted from
    * @return Returns java.util.date
    *
    */
    public java.util.Date sqlDateToDate(java.sql.Date sqlDate)
    {
        return new Date(sqlDate.getTime());
    }

    /**
    * Returns the next ID/Identity value for the table
    *
    * @param tableName the tables you want the value returned from
    * @return long the next ID/identity value
    *
    */
    public long getNextId(String tableName)
    {
        try
        {
            Connection con = getCon();
            PreparedStatement _sqlCommand = con.prepareStatement("SELECT IDENT_CURRENT(?) + IDENT_INCR(?)");
            _sqlCommand.setString(1, tableName);
            _sqlCommand.setString(2, tableName);
            String returnVal = callCommandGetField(_sqlCommand, con);

            return Long.parseLong(returnVal);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    private void sqlMonitor(long sqlExecutionTime, String dalFunction)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Running method:" + dalFunction +  System.getProperty("line.separator"));
        sb.append("Execution time: " + sqlExecutionTime + " ns" +  System.getProperty("line.separator"));
        System.out.println(sb.toString());
    }

    private boolean isClosed()
    {
        Statement stmt;
        ResultSet rs;
        try
        {
            Connection con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT 1");
            if (rs.next())
                return false; // connection is valid
        }
        catch (SQLException ex)
        {
            //ex.printStackTrace();
            return true;
        }

        return false;
    }

    protected void close(ResultSet rs, Statement ps, Connection conn)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                System.out.println("The result set cannot be closed." + e);
            }
        }
        if (ps != null)
        {
            try
            {
                ps.close();
            }
            catch (SQLException e)
            {
                System.out.println("The result set cannot be closed." + e);
            }
        }
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println("The result set cannot be closed." + e);
            }
        }
    }
}
