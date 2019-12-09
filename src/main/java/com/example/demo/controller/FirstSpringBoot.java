package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.Service.DegreeService;
import com.example.demo.domain.Degree;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FirstSpringBoot {
    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.GET)
    public Object getDegree(
            @RequestParam(value = "id" ,required = false)String id
            ) throws Exception{
            if(id==null){
                return DegreeService.getInstance().findAll();
            }else {
                Collection<Degree> degrees = DegreeService.getInstance().findAll();
                String degrees_json = JSON.toJSONString(degrees, SerializerFeature.DisableCircularReferenceDetect);
                return degrees_json;
            }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.PUT)
    public Object updateDegree(
            @RequestBody String degreeToUpdate
    ) throws Exception{
        Degree degree = JSON.parseObject(degreeToUpdate, Degree.class);
        JSONObject message = new JSONObject();
        Map<String,String> map = new HashMap<String, String>();
        if(DegreeService.getInstance().update(degree)){
            map.put("message","OK");
            return map;
        }else {
            map.put("message","The database has not changed");
            return map;
        }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.POST)
    public Object addDegree(
            @RequestBody String degreeToAdd
    ) throws Exception{
        Degree degree = JSON.parseObject(degreeToAdd, Degree.class);
        JSONObject message = new JSONObject();
        if(DegreeService.getInstance().add(degree)){
            message.put("message","OK");
            return message;
        }else {
            message.put("message","The database has not changed");
            return message;
        }
    }

    @RequestMapping(value = "/degree.ctl" , method = RequestMethod.DELETE)
    public Object deleteDegree(
            @RequestParam(value = "id")String id
    ) throws Exception{
        JSONObject message = new JSONObject();
        if(DegreeService.getInstance().delete(Integer.parseInt(id))){
            message.put("message","OK");
            return message;
        }else {
            message.put("message","The database has not changed");
            return message;
        }
    }
}
