package top.putongren.dxcblog.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.vo.UserOnlineVO;
import top.putongren.dxcblog.service.SessionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: SessionServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public DataGridResult list(int page, int limit, String username) {
        List<UserOnlineVO> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnlineVO userOnline = new UserOnlineVO();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                UserDO userDO = (UserDO) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(userDO.getUsername());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            if (!StringUtils.isEmpty(username)) {
                if (userOnline.getUsername().equals(username)) {
                    list.add(userOnline);
                }
            } else {
                list.add(userOnline);
            }
        }
        int size = list.size();
        int star = (page - 1) * limit;
        List<UserOnlineVO> listPage = list.subList(star , size-star > limit ? star + limit : size);
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(listPage);
        result.setCount(size);
        return result;
    }

    @Override
    public boolean removeUser(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
