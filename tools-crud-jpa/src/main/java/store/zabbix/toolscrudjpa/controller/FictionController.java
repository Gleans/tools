package store.zabbix.toolscrudjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.zabbix.common.entity.Fiction;
import store.zabbix.toolscrudjpa.repository.FictionRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FictionController {

    private final FictionRepository fictionRepository;

    @Autowired
    public FictionController(FictionRepository fictionRepository) {
        this.fictionRepository = fictionRepository;
    }

    @GetMapping("fiction")
    public List<Fiction> getFiction(){
        return fictionRepository.findAllById(new ArrayList<Integer>(){{
            add(1);
        }});
    }
}
