package com.project.store.service;

import com.project.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void addNewAddress() {
        Integer uid = 20;
        String username = "管理员";
        Address address = new Address();
        address.setName("张三");
        address.setPhone("17858805555");
        address.setAddress("雁塔区小寨华旗");
        iAddressService.addNewAddress(uid, username, address);
    }

    @Test
    public void getByUid() {
        List<Address> list = iAddressService.getByUid(16);
        for (Address a : list
        ) {
            System.out.println(a);
        }
    }

    @Test
    public void setDefault() {
        iAddressService.setDefault(13, 16, "祥哥");
    }

    @Test
    public void delete(){
        iAddressService.delete(13,16,"哥哥");
    }
}
