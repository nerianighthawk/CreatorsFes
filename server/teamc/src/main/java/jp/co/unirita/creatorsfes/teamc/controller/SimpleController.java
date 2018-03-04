package jp.co.unirita.creatorsfes.teamc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.service.SimpleNodeService;

@RestController
@RequestMapping(value = "/api/s1")
public class SimpleController {
    @Autowired
    SimpleNodeService simpleNodeService;

    @GetMapping(value = "overtimes")
    @ResponseStatus(HttpStatus.OK)
    public Node getCompact(@RequestParam Map<String, String> params) throws Exception{
        return simpleNodeService.execute(params);
    }

}
