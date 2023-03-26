package com.atguigu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

/*
 * Description:
 * ResponseEntity:������Ϊ�����������ķ���ֵ����ʾ��Ӧ�����������������Ӧ����
 *
 * �ļ��ϴ���Ҫ��
 * 1��form��������ʽ����Ϊpost
 * 2��form��������������enctype="multipart/form-data"
 */
@Controller
public class FileUpAndDownController {
    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //��ȡServletContext����
        ServletContext servletContext = session.getServletContext();
//��ȡ���������ļ�����ʵ·��
        String realPath = servletContext.getRealPath("img");
        realPath = realPath+ File.separator+"1.jpg";
//����������
        InputStream is = new FileInputStream(realPath);
//�����ֽ�����
        byte[] bytes = new byte[is.available()];
//���������ֽ�������
        is.read(bytes);
//����HttpHeaders����������Ӧͷ��Ϣ
        MultiValueMap<String, String> headers = new HttpHeaders();
//����Ҫ���ط�ʽ�Լ������ļ�������
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
//������Ӧ״̬��
        HttpStatus statusCode = HttpStatus.OK;
//����ResponseEntity����
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,
                statusCode);
//�ر�������
        is.close();
        return responseEntity;
    }
    @RequestMapping("/test/up")
    public String testUp(HttpSession session, MultipartFile photo) throws IOException {
        String fileName = photo.getOriginalFilename();
        //��ȡ�ϴ��ļ��ĺ�׺��
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        //��ȡUUID
        String uuid = UUID.randomUUID().toString();
        //ƴ��һ���µ��ļ���
        fileName=uuid+hzName;
        //��ȡServletContext����
        ServletContext servletContext = session.getServletContext();
        //��ȡ��ǰ���̵���ʵ·��
        String photoPath = servletContext.getRealPath("photo");
        System.out.println(photoPath);
        //����photoPath��Ӧ��File����
        File file = new File(photoPath);
        //�ж�file����Ӧ��Ŀ¼�Ƿ����
        if(!file.exists())
            file.mkdir();
        String finalPath = photoPath + File.separator + fileName;
        //�ϴ��ļ�
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
