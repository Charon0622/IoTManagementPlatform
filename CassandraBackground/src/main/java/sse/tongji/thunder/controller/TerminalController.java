package sse.tongji.thunder.controller;


import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sse.tongji.thunder.CassandraConnector;
import sse.tongji.thunder.model.Terminal;

import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @GetMapping("/getall")
    public Object getAll(){
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        List<Terminal> terminals = connector.getTerminalData();
        List<Terminal> result = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            result.add(terminals.get(i));
        }
        connector.close();
        return result;
    }

    @GetMapping("/insert")
    public Object insert(){
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            System.out.println("path:"+path.getAbsolutePath());
            FileReader fileReader = new FileReader("C:\\Users\\Stephen\\Desktop\\ter_1.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String str = null;
            CassandraConnector connector = new CassandraConnector();
            connector.connect("10.60.38.173", 9042);
            connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
            while((str = br.readLine()) != null){
                connector.getSession().execute(str);
            }
        } catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e){
            System.out.println(e);
        }
        return null;

    }

    @GetMapping("/drop")
    public String drop(){
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        connector.dropTable("terminal");
        return "successfully";
    }

    @GetMapping("/create")
    public String create(){
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        connector.createTerminalTable();
        return "successfully";
    }
}
