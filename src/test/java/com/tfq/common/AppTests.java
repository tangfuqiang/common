package com.tfq.common;


import com.tfq.common.util.ListUtil;
import com.tfq.common.util.ZipUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AppTests {


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

    @Test
    void ratioFile() throws IOException {
        File file = new File("/Users/tangfuqiang/Downloads/af-suiren");
        ZipUtil.ratioFile(file,"/Users/tangfuqiang/Downloads");

    }

}
