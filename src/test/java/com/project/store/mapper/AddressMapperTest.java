package com.project.store.mapper;

import com.project.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(18);
        address.setName("admin");
        address.setPhone("17858802974");
        address.setAddress("雁塔区小寨赛格");
        address.setIsDefault(0);
        Integer rows = addressMapper.insert(address);
        System.out.println("rows=" + rows);
    }

    @Test
    public void countByUid() {
        Integer uid = 18;
        Integer count = addressMapper.countByUid(uid);
        System.out.println(count);
    }

    @Test
    public void findByAid() {
        List<Address> byAid = addressMapper.findByUid(16);
        for (Address a : byAid) {
            System.out.println(a);
        }
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer integer = addressMapper.updateNonDefaultByUid(16);
        System.out.println(integer);
    }

    @Test
    public void updateDefaultByAid() {
        Integer rows = addressMapper.updateDefaultByAid(15, "薛哥", new Date());
        System.out.println(rows);
    }
    @Test
    public void deleteByAid() {
        Integer integer = addressMapper.deleteByAid(2);
        System.out.println(integer);
    }
    @Test
    public void findLastModified() {
        Address lastModified = addressMapper.findLastModified(16);
        System.out.println(lastModified);
    }
}


