package top.putongren.dxcblog.enums;

import java.util.Map;

/**
 * @ClassName: CodeList
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface CodeList {
    Map<String, String> getMap(String bizType);

    Map<String, String> toMap();
}
