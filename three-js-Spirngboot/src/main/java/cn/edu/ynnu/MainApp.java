package cn.edu.ynnu;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
public class MainApp {
//	静态外部文本	
	@Resource()
	public void SystemNavConfig(ThymeleafViewResolver viewResolver) {
		if (viewResolver != null) {
			Map<String, String> vars = new HashMap<>();
			systemNav sNav = new systemNav();
			vars.put("user", sNav.user);
			vars.put("title", sNav.title);
			vars.put("model", sNav.model);
			vars.put("message", sNav.message);
			vars.put("comment", sNav.comment);
			vars.put("classify", sNav.classify);
			vars.put("add", sNav.add);
			viewResolver.setStaticVariables(vars);
		}
	}
//	主程序运行
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
