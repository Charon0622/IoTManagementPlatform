package sse.tongji.thunder.controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sse.tongji.thunder.CassandraConnector;
import sse.tongji.thunder.model.Terminal2;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/terminal2")
public class Terminal2Controller {
    @GetMapping("/getall")
    public Object getAll() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("10.60.38.173", 9042);
        connector.createKeyspace("ThunderAnalysis", "SimpleStrategy", 1);
        List<Terminal2> terminals = connector.getTerminal2Data();
        connector.close();
        return terminals;
    }

    @GetMapping("/insert")
    public Object insert(){
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            System.out.println("path:"+path.getAbsolutePath());
            FileReader fileReader = new FileReader("C:\\Users\\Stephen\\Desktop\\ter_2.txt");
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
}
