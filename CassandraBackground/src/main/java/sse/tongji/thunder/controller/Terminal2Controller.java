package sse.tongji.thunder.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sse.tongji.thunder.CassandraConnector;
import sse.tongji.thunder.model.Terminal2;

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
}
