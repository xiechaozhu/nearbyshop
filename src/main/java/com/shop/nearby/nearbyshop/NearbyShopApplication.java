package com.shop.nearby.nearbyshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shop.nearby.nearbyshop.dao")
@SpringBootApplication
public class NearbyShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(NearbyShopApplication.class, args);
    }
//    @Bean
//    public ServletContext getPowers(ServletContext app){
//        Class<AdminController> clazz = AdminController.class;
//        Method[] methods = clazz.getMethods();
//        List<String> admins = new ArrayList<>();
//        List<String> agents = new ArrayList<>();
//        List<String> shoppers = new ArrayList<>();
//        for (Method method:methods){
//            //不是使用ifelse，否则多个注解将指挥被第一个if扫描
//            if(method.isAnnotationPresent(Admin.class)){
//                Admin admin = method.getAnnotation(Admin.class);
//                String value = admin.value();
//                admins.add(value);
//            }
//            if (method.isAnnotationPresent(Agent.class)){
//                Agent agent = method.getAnnotation(Agent.class);
//                String value = agent.value();
//                agents.add(value);
//            }
//            if(method.isAnnotationPresent(Shopper.class)){
//                Shopper shopper = method.getAnnotation(Shopper.class);
//                String value = shopper.value();
//                shoppers.add(value);
//            }
//        }
//        app.setAttribute("admins",admins);
//        app.setAttribute("agents",agents);
//        app.setAttribute("shoppers",shoppers);
//        return app;
//    }
}
