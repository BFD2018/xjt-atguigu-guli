package com.xjt.edutest.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xjt.edutest.excel.entity.Question1;
import com.xjt.edutest.excel.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestEasyExcel {
    @Test
    public void testWrite() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student data = new Student();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }

        //1.实现excel写的操作
        //1.1 设置写入文件夹地址和excel文件名称
        String filename = "D:\\CodeLearning\\JavaLearning\\JavaProjects\\my-javaweb-projects\\xjt-atguigu-guli\\guli-parent\\service\\service-edu\\src\\test\\java\\com\\xjt\\edutest\\excel\\entity\\write.xlsx";
        //1.2 调用easyexcel里面的方法实现写操作
        //write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, Student.class).sheet("学生列表").doWrite(list);
    }

    @Test
    public void testRead01() {
        //实现excel读操作
        String filename = "D:\\CodeLearning\\JavaLearning\\JavaProjects\\my-javaweb-projects\\xjt-atguigu-guli\\guli-parent\\service\\service-edu\\src\\test\\java\\com\\xjt\\edutest\\excel\\entity\\write.xlsx";
        EasyExcel.read(filename, Student.class, new ExcelListener()).sheet().doRead();
    }


    @Test
    public void testRead02() {
        final List list = new ArrayList();

        //使用EasyExcel读取test1.xlsx文件
        EasyExcel.read("D:\\CodeLearning\\JavaLearning\\JavaProjects\\my-javaweb-projects\\xjt-atguigu-guli\\guli-parent\\service\\service-edu\\src\\test\\java\\com\\xjt\\edutest\\excel\\entity\\test1.xlsx",
                Question1.class,
                new AnalysisEventListener<Question1>() {
                    //重写子类方法
                    @Override
                    public void invoke(Question1 question1, AnalysisContext analysisContext) {
                        list.add(question1);
                    }

                    //重写子类方法
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("==========>全部读完了");
                    }

                    @Override
                    public void invokeHeadMap(Map headMap, AnalysisContext context) {
                        System.out.println("读了第一行------->" + headMap);
                    }
                }
        ).doReadAll();

        //获取读取到的数据
        for (Object o : list) {
            Question1 question1 = (Question1) o;
            System.out.println(question1);
        }
    }


}
