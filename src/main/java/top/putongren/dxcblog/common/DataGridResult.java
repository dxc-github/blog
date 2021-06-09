package top.putongren.dxcblog.common;

import java.util.List;

/**
 * 功能描述: 数据返回实体
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 11:16
 */
public class DataGridResult {
    private int code;
    private String message;
    private long count;
    private List<?> data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public List<?> getData() {
        return data;
    }
    public void setData(List<?> data) {
        this.data = data;
    }

}
