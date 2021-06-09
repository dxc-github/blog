package top.putongren.dxcblog.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;
import top.putongren.dxcblog.dao.UserDao;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.service.MenuService;
import top.putongren.dxcblog.web.config.ApplicationContextRegister;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = ((UserDO)SecurityUtils.getSubject().getPrincipal()).getId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        String password = new String((char[]) authenticationToken.getCredentials());

        UserDao userDao = ApplicationContextRegister.getBean(UserDao.class);

        UserDO user = null;
        if(username.length()>12){
            user = userDao.getUserByQqOpenId(username);
            //账号不存在
            if(user == null){
                throw new UnknownAccountException("账号或密码不正确");
            }
            //账号被锁定
            if(user.getEnable() == false){
                throw new LockedAccountException("账号已被锁定，请联系管理员");
            }
        } else {
            user = userDao.getUserByName(username);
            //账号不存在
            if(user == null){
                throw new UnknownAccountException("账号或密码不正确");
            }

            //密码错误
            if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
                throw new IncorrectCredentialsException("账号或密码不正确");
            }

            //账号锁定
            if(user.getEnable() == false){
                throw new LockedAccountException("账号已被锁定，请联系管理员");
            }
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
