package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;

/**
 * @ClassName: SessionService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface SessionService {
    /**获取在线用户*/
    DataGridResult list(int page, int limit, String username);

    /**强制退出*/
    boolean removeUser(String sessionId);
}
