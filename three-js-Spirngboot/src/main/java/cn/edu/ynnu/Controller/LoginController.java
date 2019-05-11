package cn.edu.ynnu.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.ynnu.model.adminUser;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.model.yh;
import cn.edu.ynnu.service.LoginService;
import cn.edu.ynnu.service.TitleService;
import cn.edu.ynnu.service.UserService;

@Controller

public class LoginController {
	@Autowired
	private LoginService longinService;
	@Autowired
	private UserService userService;

	@Autowired
	private TitleService titleService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String SignIn() {
		return "back-stage/login";
	}

	@RequestMapping(value = "/bs-login", method = RequestMethod.POST)
	public String adminLogin(@RequestParam String login_user, @RequestParam String login_pwd, Model model,
			HttpServletRequest request, HttpSession session) throws Exception {
		List<adminUser> admin = longinService.getAllUser();
		session = request.getSession();
		try {
			if (login_user.isEmpty() || login_pwd.isEmpty()) {
				model.addAttribute("error", 1);
				return "back-stage/login";
			} else {
				for (adminUser a : admin) {
					if (login_user.equals(a.getUsername()) && login_pwd.equals(a.getPassword())) {
						session.setAttribute("admin_name", a.getAdmin_name());
						session.setAttribute("admin_id", a.getId());
						return "redirect:/system";
					}
				}
				model.addAttribute("error", 2);
				return "back-stage/login";
			}

		} catch (Exception e) {
			System.out.println("错误:" + "class:LoginController" + "method:adminLogin");
			e.printStackTrace();
		}
		return "back-stage/login";
	}

	@RequestMapping(value = "/bs-logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "sign-in", method = RequestMethod.POST)
	public String signIn(Model model, HttpSession session, HttpServletRequest request, @RequestParam String username,
			@RequestParam String password) {
		session = request.getSession();
		if (!username.isEmpty() && !password.isEmpty()) {
			List<yh> user = userService.findAll();
			for (yh yh : user) {
				String getUsername = yh.getYh_username();
				String getPassword = yh.getYh_password();
				if (username.equals(getUsername) && password.equals(getPassword)) {
					String getNc = yh.getYh_nc();
					int getId = yh.getYh_id();
					getNc.subSequence(0, 2);

					session.setAttribute("state", "1");
					session.setAttribute("yh_nc", getNc);
					session.setAttribute("yh_id", getId);

					return "redirect:/index";
				}
			}
			session.setAttribute("state", "2");
			return "redirect:/index";
		}
		session.setAttribute("state", "3");
		return "redirect:/index";
	}

	@RequestMapping(value = "signout")
	public String signout(HttpSession session, HttpServletRequest request) {
		try {
			session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "zc", method = RequestMethod.POST)
	public String zcLogin(Model model, @RequestParam String yh_user, @RequestParam String yh_pass1,
			@RequestParam String yh_pass2, @RequestParam String yh_nc, @RequestParam String yh_email,
			@RequestParam String yh_phone) {
		List<webTitle> title = titleService.getAll();
		model.addAttribute("TitleList", title);
		if (yh_user.isEmpty()) {
			model.addAttribute("error", "1");
			return "account";
		}
		if (yh_nc.isEmpty()) {
			model.addAttribute("error", "2");
			return "account";
		}
		if (yh_phone.isEmpty()) {
			model.addAttribute("error", "3");
			return "account";
		}
		if (yh_email.isEmpty()) {
			model.addAttribute("error", "4");
			return "account";
		}
		if (yh_pass1.isEmpty()) {
			model.addAttribute("error", "5");
			return "account";
		}
		if (!yh_pass1.equals(yh_pass2)) {
			model.addAttribute("error", "6");
			return "account";
		}
		if (!yh_user.isEmpty()) {
			if (userService.myfindByusername(yh_user) != null) {
				model.addAttribute("error", "7");
				return "account";
			}

		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String yh_date = df.format(new Date());

		yh yhList = new yh();
		yhList.setYh_username(yh_user);
		yhList.setYh_nc(yh_nc);
		yhList.setYh_phone(yh_phone);
		yhList.setYh_password(yh_pass1.trim());
		yhList.setYh_email(yh_email);
		yhList.setYh_zcdate(yh_date);
		userService.save(yhList);
		return "redirect:/index";

	}

}