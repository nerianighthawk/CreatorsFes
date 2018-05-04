package jp.co.unirita.creatorsfes.teamc.controller;


import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/masters")
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping(value = "/{masterName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Master> getMasterData(
            @PathVariable(value = "masterName")String masterName,
            @RequestParam Map<String, String> options
    ) throws Exception {
        return masterService.execute(masterName, options);
    }
}
