package jp.co.unirita.creatorsfes.teamc.controller;


import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/masters")
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping(value = "/{masterName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Master> getMasterData(
            @PathVariable(value = "masterName")String masterName
    ) throws Exception {
        return masterService.execute(masterName);
    }
}
