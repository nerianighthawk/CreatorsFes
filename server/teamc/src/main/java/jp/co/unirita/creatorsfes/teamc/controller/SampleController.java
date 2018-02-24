package jp.co.unirita.creatorsfes.teamc.controller;

import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/overtimes")
public class SampleController {

    @Autowired
    NodeService nodeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Node getovertimes(@RequestParam Map<String, String> params) throws Exception{
        return nodeService.execute(params);
    }
}
