package jp.co.unirita.creatorsfes.teamc.controller;

import jp.co.unirita.creatorsfes.teamc.model.NodeData;
import jp.co.unirita.creatorsfes.teamc.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/overtimes")
public class OverTimesController {

    @Autowired
    NodeService nodeService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public NodeData getOvertimes(@RequestParam Map<String, String> params) throws Exception{
        return nodeService.execute(params, false);
    }

    @GetMapping(value = "/detail")
    @ResponseStatus(HttpStatus.OK)
    public NodeData getDetail(@RequestParam Map<String, String> params) throws Exception {
        return nodeService.execute(params, true);
    }

    @GetMapping(value = "/pileup")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<Double>> getPileupOvertimes(
            @RequestParam(name = "set") int set,
            @RequestParam(name = "year") int year,
            @RequestParam Map<String, String> params
    )throws Exception {
        params.remove("year");
        params.remove("set");
        return nodeService.pileUp(year, set, params);
    }
}
