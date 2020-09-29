package com.tfq.common;

import com.tfq.common.util.ListUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CommonApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void sortList(){
        Stusent stusent = new Stusent("abc",112);
        Stusent stusent1 = new Stusent("abd",81);
        List<Stusent> list = new ArrayList<>();
        list.add(stusent);
        list.add(stusent1);
        ListUtil.sortList(list,false,"name","age");
        for (Stusent s:list
        ) {
            System.out.println(s.toString());
        }
    }

}
