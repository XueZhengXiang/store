package com.project.store.controller;

import com.project.store.entity.District;
import com.project.store.service.IDistrictService;
import com.project.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService iDistrictService;

    /**
     * {"state":200,"data":[{"id":null,"parent":null,"code":"110000","name":"北京市"},
     * {"id":null,"parent":null,"code":"120000","name":"天津市"},{"id":null,"parent":null,
     * "code":"130000","name":"河北省"},{"id":null,"parent":null,"code":"140000","name":"山西省"},
     * {"id":null,"parent":null,"code":"150000","name":"内蒙古自治区"},{"id":null,"parent":null,
     * "code":"210000","name":"辽宁省"},{"id":null,"parent":null,"code":"220000","name":"吉林省"},
     * {"id":null,"parent":null,"code":"230000","name":"黑龙江省"},{"id":null,"parent":null,
     * "code":"310000","name":"上海市"},{"id":null,"parent":null,"code":"320000","name":"江苏省"},
     * {"id":null,"parent":null,"code":"330000","name":"浙江省"},{"id":null,"parent":null,"code":
     * "340000","name":"安徽省"},{"id":null,"parent":null,"code":"350000","name":"福建省"},{"id":null,"parent":null,"code":"360000","name":"江西省"},{"id":null,"parent":null,"code":"370000","name":"山东省"},{"id":null,"parent":null,"code":"410000","name":"河南省"},{"id":null,"parent":null,"code":"420000","name":"湖北省"},{"id":null,"parent":null,"code":"430000","name":"湖南省"},{"id":null,"parent":null,"code":"440000","name":"广东省"},{"id":null,"parent":null,"code":"450000","name":"广西壮族自治区"},{"id":null,"parent":null,"code":"460000","name":"海南省"},{"id":null,"parent":null,"code":"500000","name":"重庆市"},{"id":null,"parent":null,"code":"510000","name":"四川省"},{"id":null,"parent":null,"code":"520000","name":"贵州省"},{"id":null,"parent":null,"code":"530000","name":"云南省"},{"id":null,"parent":null,"code":"540000","name":"西藏自治区"},{"id":null,"parent":null,"code":"610000","name":"陕西省"},{"id":null,"parent":null,"code":"620000","name":"甘肃省"},{"id":null,
     * "parent":null,"code":"630000","name":"青海省"},{"id":null,"parent":null,"code":"640000","name":"宁夏回族自治区"},{"id":null,"parent":null,"code":"650000","name":"新疆维吾尔自治区"},{"id":null,"parent":null,"code":"710000","name":"台湾省"},{"id":null,"parent":null,"code":"810000","name":"香港特别行政区"},{"id":null,"parent":null,"code":"820000","name":"澳门特别行政区"}],"message":null}
     * @param parent
     * @return
     */
    @GetMapping({"", "/"})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = iDistrictService.getByParent(parent);
        return new JsonResult<>(OK, data);
    }
}



















