package nju.agile.dianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@SpringBootApplication(scanBasePackages = {"nju.agile.dianping"})
//@MapperScan("nju.agile.dianping.dal")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
//public class DianpingApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(DianpingApplication.class, args);
//    }
//}


@SpringBootApplication(scanBasePackages = {"nju.agile.dianping"})
@MapperScan("nju.agile.dianping.dal")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DianpingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DianpingApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DianpingApplication.class);
    }

}