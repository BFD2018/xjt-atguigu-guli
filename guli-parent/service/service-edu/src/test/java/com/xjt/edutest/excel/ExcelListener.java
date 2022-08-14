package com.xjt.edutest.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xjt.edutest.excel.entity.Student;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<Student>{

    //一行一行读取excel内容
    @Override
    public void invoke(Student data, AnalysisContext analysisContext) {
        System.out.println("每次读一行数据都会调用===>"+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("读表头调用："+headMap);
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("--------->全部读完了");
    }
}
