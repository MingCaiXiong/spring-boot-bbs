package top.xiongmingcai.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//作用是扫描Mapper接口 在编译之后会生成相应的接口实现类
@MapperScan(basePackages = "top.xiongmingcai.bbs.model.dao")
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class, args);
    }

}
