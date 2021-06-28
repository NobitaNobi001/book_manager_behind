package com.design.book;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.design.book.mapper.BookMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.print.Book;

/**
 * @author ezuy
 * @date 21/5/25 14:58
 */
public class Test {

//    @org.junit.Test
    public void generateCode(){

        // 1.创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("ezuy");
        // 是否打开资源管理器
        gc.setOpen(false);
        // 去掉Service接口的首字母I
        gc.setServiceName("%sService");
        // 主键策略
        gc.setIdType(IdType.AUTO);
        // 开启Swagger2模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/book?serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("YuZe0703");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.design.book");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库字段表命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok
        strategy.setEntityLombokModel(true);
        // 逻辑删除字段名
        strategy.setLogicDeleteFieldName("is_deleted");
        // 去掉布尔值的is_前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 6.执行
        mpg.execute();
    }

//    @org.junit.Test
    public void test(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        Client client = dcf.createClient("http://192.168.43.194:9000/WebService?wsdl");

        Object[] objects;
        try{

            objects = client.invoke("selectAllBooks");
            System.out.println(objects[0]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Autowired
    public static ConfigurableApplicationContext configurableApplicationContext;

//    @org.junit.Test
    public void testBean(){
        System.out.println(configurableApplicationContext.getBean(BookMapper.class));
    }
}