package com.order.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.dto.UserDto;
import com.order.exception.OperationFailedException;
import com.order.pojo.User;
import com.order.service.UserService;
import io.netty.util.internal.StringUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.order.constant.UserConstant.*;

/**
 * @author ：clt
 * @Date ：Created in 16:30 2019/8/9
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public String userReg() {
        return "/user/Register";
    }

    @RequestMapping("/upd")
    public String userUpd() {
        return "/user/UserUpdate";
    }

    @RequestMapping("/man")
    public String userMan() {
        return "/user/UserManager";
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> userRegister(@RequestBody User user) {
        user.setRegTime(new Date());
        user.setLastLoginTime(new Date());
        Object md5PassWord = new SimpleHash("MD5",user.getPassWord(),
                                        user.getUserName(), 1024);
        user.setPassWord(md5PassWord.toString());
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.insertOneUser(user);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        map.put("user", user);
        return map;
    }

    @RequestMapping(value = "/registerCheck",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerCheck(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>(16);
        if (null == user || "".equals(user.getUserName())){
            map.put("code", REGISTER_USERNAME_NULL);
            return map;
        }
        List<User> users = userService.findByUserName(user.getUserName());
        map.put("code", REGISTER_USERNAME_AVAILABLE);
        if (!Objects.isNull(users) && users.size() != 0) {
            System.out.println(users);
            map.put("code", REGISTER_USERNAME_REPEAT);
        }
        return map;
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<User> userList(@RequestParam(value = "currentPage",defaultValue = "1") String currentPage,
                                   @RequestParam(value = "pageSize",defaultValue = "10") String pageSize) {
        int pn = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        PageHelper.startPage(pn, size);
        List<User> userList = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @RequestMapping("/listByCondiction")
    @ResponseBody
    public PageInfo<User> userlistByCondiction(@RequestBody UserDto userDto) {
//        PageHelper.startPage(userDto.getCurrentPage(), userDto.getPageSize());
        PageHelper.startPage(userDto.getCurrentPage(), userDto.getPageSize());
        List<User> userList = userService.findByCondiction(userDto);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userUpdate(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.updateOneUser(user);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        map.put("user", user);
        return map;
    }



    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> userDelete(@RequestParam("userId") Integer userId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.deleteOneUser(userId);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        if (OPERATION_SUCCESS == (Integer) map.get("code")) {
            PageHelper.startPage(1, 10);
            List<User> userList = userService.findAllUser();
            PageInfo<User> userpageInfo = new PageInfo<>(userList);
            map.put("PageInfo", userpageInfo);
        }
        return map;
    }

    @RequestMapping("/findUser")
    public String findUser(@RequestParam("userId") Integer userId, Model model){
        User user=userService.findByUserId(userId);
        model.addAttribute("userId",userId);
        return "/user/UserUpdate";
    }

    @RequestMapping("/finduser")
    @ResponseBody
    public User test(@RequestParam("userId") Integer userId){
        User user=userService.findByUserId(userId);
        return user;
    }

//    @RequestMapping("/update")
//    @ResponseBody
//    public User update(User user,String uId){
//        User u;
//        if(uId!=null){
//            u=userService.selectByuserId(Integer.parseInt(uId));
//            System.out.println(uId+"#####################");
//        }else{
//            userService.updateUser(user);
//            u=userService.selectByuserId(user.getUserId());
//            System.out.println(user+"#####################");
//        }
//        return u;
//    }

}
