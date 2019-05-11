package cn.edu.ynnu;

import java.util.List;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.edu.ynnu.model.adminUser;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.repository.TitleRepository;
import cn.edu.ynnu.service.LoginService;
import cn.edu.ynnu.service.TitleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MainApp.class })
public class AppTest {
	@Autowired
	private LoginService loginService;
	@Autowired
	private TitleService titleService;
	@Test
	public void FindAll() {
		List<adminUser> a = loginService.getAllUser();
		for (adminUser adminUser : a) {
			System.out.println(adminUser.getUsername());
			System.out.println(adminUser.getPassword());
			System.out.println(adminUser.getFlag());
			System.out.println(adminUser.getDate().toString());

		}
	}
	@Test
	public void FindTitle() {
		List<webTitle> a = titleService.getAll();
		for (webTitle webTitle : a) {
			System.out.println(webTitle.getCzy_name());
		}
	}
}
