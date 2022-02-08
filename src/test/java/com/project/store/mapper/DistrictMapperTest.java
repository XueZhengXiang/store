package com.project.store.mapper;

import com.project.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper mapper;

    @Test
    public void findByParent() {
        List<District> byParent = mapper.findByParent("210600");
        for (District d : byParent) {
            System.out.println(d);
        }
    }
    @Test
    public void findNameByCode(){
        String nameByCode = mapper.findNameByCode("110000");
        System.out.println(nameByCode);
    }
}
