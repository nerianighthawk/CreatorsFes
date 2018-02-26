package jp.co.unirita.creatorsfes.teamc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.service.MasterService;
import jp.co.unirita.creatorsfes.teamc.service.NodeService;

@RestController
@RequestMapping(value = "/api/v1")
public class SampleController {

    @Autowired
    NodeService nodeService;

    @Autowired
    MasterService masterService;
    
    @GetMapping(value = "overtimes")
    @ResponseStatus(HttpStatus.OK)
    public Node getovertimes(@RequestParam Map<String, String> params) throws Exception{
        return nodeService.execute(params, false);
    }

    @GetMapping(value = "overtimes/detail")
    @ResponseStatus(HttpStatus.OK)
    public Node getDetail(@RequestParam Map<String, String> params) throws Exception {
        return nodeService.execute(params, true);
    }

    @GetMapping(value = "/masters/{columnName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Master> getMasterData(
            @PathVariable(value = "columnName")String columnName
    ) throws Exception {
        return masterService.execute(columnName);
    }
}
