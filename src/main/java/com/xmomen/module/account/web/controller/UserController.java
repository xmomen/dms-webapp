package com.xmomen.module.account.web.controller;

import javax.validation.Valid;

import com.xmomen.module.account.model.CreateUser;
import com.xmomen.module.account.mapper.UserMapper;
import com.xmomen.module.account.model.User;
import com.xmomen.module.account.service.UserService;
import com.xmomen.module.account.web.controller.vo.CreateUserVo;
import com.xmomen.module.account.web.controller.vo.UpdateUserVo;
import com.xmomen.module.user.entity.SysUsers;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.xmomen.framework.mybatis.dao.MybatisDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MybatisDao mybatisDao;

    /**
     *  用户列表
     * @param id
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @Log(actionName = "查询用户列表")
    public Page<User> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        Map map = new HashMap<String,Object>();
        map.put("id", id);
        map.put("keyword", keyword);
        return (Page<User>) mybatisDao.selectPage(UserMapper.UserMapperNameSpace + "getUsers", map, limit, offset);
    }

    /**
     *  用户列表
     * @param id
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询用户")
    public SysUsers getUserList(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysUsers.class, id);
    }

    /**
     * 新增用户
     * @param createUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @Log(actionName = "新增用户")
    public SysUsers createUser(@RequestBody @Valid CreateUserVo createUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CreateUser user = new CreateUser();
        user.setUsername(createUser.getUsername());
        user.setPassword(createUser.getPassword());
        user.setEmail(createUser.getEmail());
        user.setLocked(createUser.getLocked() != null && createUser.getLocked() == true ? true : false);
        return userService.createUser(user);
    }

    /**
     * 更新用户
     * @param id
     * @param updateUserVo
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新用户")
    public void updateUser(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateUserVo updateUserVo, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        userService.updateUser(updateUserVo);
    }

    /**
     *  删除用户
     * @param id
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除用户")
    public void deleteUser(@PathVariable(value = "id") Long id){
        mybatisDao.deleteByPrimaryKey(SysUsers.class, id);
    }

    /**
     *  锁定用户
     * @param id
     */
    @RequestMapping(value = "/user/{id}/locked", method = RequestMethod.PUT)
    @Log(actionName = "修改用户信息")
    public void lockedUser(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "locked") Boolean locked){
        SysUsers sysUsers = new SysUsers();
        sysUsers.setLocked(locked ? 1 : 0);
        sysUsers.setId(id);
        mybatisDao.update(sysUsers);
    }

}
