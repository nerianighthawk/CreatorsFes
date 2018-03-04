package jp.co.unirita.creatorsfes.teamc.controller;

import jp.co.unirita.creatorsfes.teamc.service.AxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/axes")
public class AxisController {

    @Autowired
    AxisService axisService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getKeySet() throws Exception {
        return axisService.getAxes();
    }

    @GetMapping(value = "/{axisName}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAxisValues(@PathVariable(value = "axisName")String axisName) throws Exception {
        return axisService.getValues(axisName);
    }
}
