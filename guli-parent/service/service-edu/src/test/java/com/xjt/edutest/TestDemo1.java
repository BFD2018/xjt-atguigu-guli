package com.xjt.edutest;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class TestDemo1 {
    @Test
    public void generateCode(){
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");        //这个方法获取的目录有时候不准确，这里我们直接指定代码生成的绝对路径（重要！）
        gc.setOutputDir("D:\\CodeLearning\\JavaLearning\\JavaProjects\\my-javaweb-projects\\guli-parent\\service\\service-edu\\src\\main\\java");

        gc.setAuthor("JavaXiong");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false);  //重新生成时文件是否覆盖（重要！）

        //IUserServie 默认生成的方法前面带一个I
        gc.setServiceName("%sService");	//去掉Service接口的首字母I

        gc.setIdType(IdType.ID_WORKER_STR);     //主键策略（重要！）
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置（重要！）
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/atguigu_guli?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置（重要！）
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice"); //模块名
        //包  com.xjt.eduservice
        pc.setParent("com.xjt");
        //包  com.xjt.eduservice.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置（重要！）
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude("edu_teacher");     //数据库中对应的表，可以写多个

        strategy.setNaming(NamingStrategy.underline_to_camel);  //数据库表映射到实体的命名策略（下划线转驼峰）
        strategy.setTablePrefix(pc.getModuleName() + "_");      //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);    //数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true);    // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }

}
