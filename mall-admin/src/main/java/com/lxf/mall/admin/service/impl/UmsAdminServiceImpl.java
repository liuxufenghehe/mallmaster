package com.lxf.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.lxf.mall.admin.bo.AdminUserDetails;
import com.lxf.mall.admin.dao.UmsAdminRoleRelationDao;
import com.lxf.mall.admin.dto.UmsAdminParam;
import com.lxf.mall.admin.service.UmsAdminService;
import com.lxf.mall.common.util.BrowerTypeUtil;
import com.lxf.mall.mbg.bo.UmsAdmin;
import com.lxf.mall.mbg.bo.UmsAdminExample;
import com.lxf.mall.mbg.bo.UmsAdminLoginLog;
import com.lxf.mall.mbg.bo.UmsPermission;
import com.lxf.mall.mbg.mapper.UmsAdminLoginLogMapper;
import com.lxf.mall.mbg.mapper.UmsAdminMapper;
import com.lxf.mall.security.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author xufeng.liu
 * @email xueshzd@163.com
 * @date 2019/12/11 9:34
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
        if (umsAdmins != null && umsAdmins.size() > 0){
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long id) {
        List<UmsPermission> umsPermissions = umsAdminRoleRelationDao.getPermissionList(id);
        return umsPermissions;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam,umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //判断用户名是否被注册
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if(umsAdmins != null && umsAdmins.size() > 0){
            return null;
        }
        //注册到数据库
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        int count = umsAdminMapper.insert(umsAdmin);
        if(count == 0){
            return null;
        }
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //生成token
            token = jwtTokenUtil.generateToken(userDetails);
            //更新登录时间
            updateLoginlTimeByUsername(username);
            //添加登录日志
            insertLoginLog(username);
        }catch (AuthenticationException e){
            LOGGER.error("登录异常：{}",e.getMessage());
        }
        return token;
    }

    private void insertLoginLog(String username) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String header = request.getHeader("USER-AGENT");
        String browserName = BrowerTypeUtil.getBrowserName(header);
        loginLog.setIp(request.getRemoteAddr());
        loginLog.setUserAgent(browserName);
        loginLogMapper.insert(loginLog);

    }

    private void updateLoginlTimeByUsername(String username) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        umsAdminMapper.updateByExampleSelective(umsAdmin,example);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        if(umsAdmin != null){
            List<UmsPermission> permissionList = getPermissionList(umsAdmin.getId());
            return new AdminUserDetails(umsAdmin,permissionList);
        }
        throw new UsernameNotFoundException("用户名称不存在！");
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(keyword)){
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andUsernameLike("%" + keyword + "%"));
        }
        return umsAdminMapper.selectByExample(example);
    }

}
