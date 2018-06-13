package sse.tongji.thunder;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import sse.tongji.thunder.model.Terminal;
import sse.tongji.thunder.model.Terminal2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CassandraConnector {

    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    /**
     *
     * @param keyspaceName 键空间名字
     * @param replicationStrategy 它只是把副本放在戒指中的策略。我们有简单策略（机架感知策略），旧网络拓扑策略（机架感知策略）和网络拓扑策略（数据中心共享策略）等策略。
     * @param replicationFactor 它是集群中将接收相同数据副本的计算机数
     */
    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor){
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
        session.execute(" USE " + keyspaceName + ";");
    }

    /**
     * 查看现有的键空间
     */
    public void checkKeySpace(){
        ResultSet resultSet = session.execute("SELECT * FROM system_schema.keyspaces;");
        System.out.println(resultSet.all());
    }

    public void createTerminalTable(){
        String query = "CREATE TABLE IF NOT EXISTS terminal" +
                "(" + "terminal_description text, "+
                "peak float, "+
                "lasting_time int,"+
                "polarity text,"+
                "date timestamp," +
                "unit1 text," +
                "unit2 text," +
                "PRIMARY KEY (terminal_description, date));"; //'2015-05-18 09:00'
        session.execute(query);
    }

    public void createTerminal2Table(){
        String query = "CREATE TABLE IF NOT EXISTS terminal2" +
                "(" + "terminal_description text PRIMARY KEY, "+
                "resistance float, "+
                "earthing text,"+
                "SPD text,"+
                "kk text," +
                "UA float," +
                "UB float," +
                "UC float," +
                "IA float," +
                "IB float," +
                "IC float," +
                "IrA float," +
                "IrB float," +
                "IrC float," +
                "humidity float," +
                "temperature float);";
        session.execute(query);
    }

    public void dropTable(String tableName){
        session.execute("DROP TABLE " + tableName + ";");
    }

    public void insertTestData(){
//        List<String> data = new ArrayList<String>();
//        data.add("INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2)"
//                + " VALUES('4#雷电分析仪',2.1,372,'正','2018-03-05 06:51:58','千安','微秒');");
//        data.add("INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2)"
//                + " VALUES('4#雷电分析仪',1.3,598,'正','2018-03-05 06:39:35','千安','微秒');");
//        for(int i = 0; i < data.size(); i++){
//            session.execute(data.get(i));
//        }

        List<String> data = new ArrayList<String>();
        data.add("INSERT INTO terminal2 (terminal_description, resistance, earthing, SPD, kk, UA, UB, UC, IA, IB, IC,"
                +"IrA, IrB, IrC, humidity, temperature)"
                +" VALUES('4#雷电分析仪', 2.1, '是', '正常', '闭合', 227.9, 220.4, 230.1, 49.3, 52.2, 33.2,"
                +"246.0, 60.0, 143.0, 7.1, 23.4)");
        for (int i = 0; i < data.size(); i++){
            session.execute(data.get(i));
        }
    }

    public List<Terminal> getTerminalData(){
        ResultSet resultSet = session.execute("select terminal_description,peak,lasting_time,polarity,date,unit1,unit2 from terminal");
        List<Row> data = resultSet.all();
        System.out.println(data);
        List<Terminal> dataset = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            Terminal terminal = new Terminal();
            terminal.setTerminal_description(data.get(i).get(0, String.class));
            terminal.setPeak(data.get(i).get(1, Float.class));
            terminal.setLasting_time(data.get(i).get(2, Integer.class));
            terminal.setPolarity(data.get(i).get(3, String.class));
            terminal.setDate(data.get(i).get(4, Date.class));
            terminal.setUnit1(data.get(i).get(5, String.class));
            terminal.setUnit2(data.get(i).get(6, String.class));
            dataset.add(terminal);
        }
        return dataset;
    }

    public List<Terminal2> getTerminal2Data(){
        ResultSet resultSet = session.execute("select terminal_description,resistance,earthing,SPD,kk,UA,UB,UC,IA,IB,IC,IrA,IrB,IrC,humidity,temperature from terminal2");
        List<Row> data = resultSet.all();
        System.out.println(data);
        List<Terminal2> dataset = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            Terminal2 terminal = new Terminal2();
            terminal.setTerminal_description(data.get(i).get(0, String.class));
            terminal.setResistance(data.get(i).get(1, Float.class));
            terminal.setEarthing(data.get(i).get(2, String.class));
            terminal.setSPD(data.get(i).get(3, String.class));
            terminal.setKk(data.get(i).get(4, String.class));
            terminal.setUA(data.get(i).get(5, Float.class));
            terminal.setUB(data.get(i).get(6, Float.class));
            terminal.setUC(data.get(i).get(7, Float.class));
            terminal.setIA(data.get(i).get(8, Float.class));
            terminal.setIB(data.get(i).get(9, Float.class));
            terminal.setIC(data.get(i).get(10, Float.class));
            terminal.setIrA(data.get(i).get(11, Float.class));
            terminal.setIrB(data.get(i).get(12, Float.class));
            terminal.setIrC(data.get(i).get(13, Float.class));
            terminal.setHumidity(data.get(i).get(14, Float.class));
            terminal.setTemperature(data.get(i).get(15, Float.class));
            dataset.add(terminal);
        }

        return dataset;
    }

    public static void main(String[] args){
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
//        connector.dropTable("terminal");
        connector.createTerminalTable();
        connector.createTerminal2Table();
//        connector.insertTestData();
        connector.getTerminal2Data();

        connector.close();
    }
}
