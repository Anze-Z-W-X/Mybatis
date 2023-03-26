package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/*
 * Description:
 * 1��@RequestBody�����������е����ݺͿ������������βν��а�
 * 2��ʹ��@RequestBodyע�⽫json��ʽ���������ת��Ϊjava����
 * a>����jackson������
 * b>��SpringMVC�������ļ�������<mvc:annotation-driven />
 * c>�ڴ�������Ŀ������������β�λ�ã�ֱ������json��ʽ���������Ҫת����java���͵��βΣ�ʹ��@RequestBodyע���ʶ����
 * 3��@ResponseBody��������ʶ�Ŀ����������ķ���ֵ��Ϊ��Ӧ���ĵ���Ӧ����Ӧ�������
 * 4��ʹ��@ResponseBodyע����Ӧ�����json��ʽ������
 * a>����jackson������
 * b>��SpringMVC�������ļ�������<mvc:annotation-driven />
 * c>����Ҫת��Ϊjson�ַ�����java����ֱ����Ϊ�����������ķ���ֵ��ʹ��@ResponseBodyע���ʶ����������
 * �Ϳ��Խ�java����ֱ��ת��Ϊjson�ַ���������Ӧ�������
 *
 * ���õ�Java����ת��Ϊjson�Ľ����
 * ʵ����-->json����
 * map-->json����
 * list-->json����
 */
@Controller
//@RestController�൱��@Controller+@ResponseBody
public class TestAjaxController {
    @RequestMapping(value = "/test/ajax")
    public void testAjax(Integer id,@RequestBody String requestBody ,HttpServletResponse response) throws IOException {
        System.out.println("requestBody:"+requestBody);
        System.out.println("id:"+id);
        response.getWriter().write("hello,axios");
    }
    @RequestMapping("/test/RequestBody/json")
    public void testRequestBody(@RequestBody Map<String ,Object>map, HttpServletResponse response) throws IOException {
        System.out.println(map);
        response.getWriter().write("hello,RequestBody!");
    }
    @RequestMapping("/test/ResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "hello,ResponseBody!";
    }

    @RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public User testResponseBodyJson(){
        User user = new User(1001,"admin","123",20,"��");
        return user;
    }
}
