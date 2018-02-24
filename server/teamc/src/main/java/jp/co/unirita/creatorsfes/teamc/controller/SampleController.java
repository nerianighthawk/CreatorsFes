package jp.co.unirita.creatorsfes.teamc.controller;

import jp.co.unirita.creatorsfes.teamc.model.Master;
import jp.co.unirita.creatorsfes.teamc.model.Node;
import jp.co.unirita.creatorsfes.teamc.service.NodeService;
import jp.co.unirita.creatorsfes.teamc.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class SampleController {

    @Autowired
    NodeService nodeService;

    @GetMapping(value = "overtimes")
    @ResponseStatus(HttpStatus.OK)
    public Node getovertimes(
            @RequestParam(value = "getRecord", required = true)boolean getRecord,
            @RequestParam Map<String, String> params ) throws Exception{
        return nodeService.execute(params, getRecord);
    }

    @GetMapping(value = "/masters/{columnName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Master> getMasterData(
            @PathVariable(value = "columnName")String columnName
    ) throws Exception {
        return FileUtil.loadMasterData(columnName);
    }
}
