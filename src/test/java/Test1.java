import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.putongren.dxcblog.service.MenuService;
import top.putongren.dxcblog.service.impl.MenuServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: Test1
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
@SpringBootTest
public class Test1 {
    private MenuService menuService = new MenuServiceImpl();

    @Test
    public void test2(){
        menuService.listPerms(new Long(2));
    }

    public static void main(String[] args) {
//        String a = "blog:manage:index";
//        List<String> alist = Arrays.asList(a.trim().split(":"));
//        for(String s:alist ){
//            System.out.println(s);
//        }
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out :: println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out :: println);


    }

}
