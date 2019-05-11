package cn.edu.ynnu.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ynnu.model.fl;
import cn.edu.ynnu.model.mx;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.model.yh;
import cn.edu.ynnu.service.FLService;
import cn.edu.ynnu.service.MxService;
import cn.edu.ynnu.service.TitleService;
import cn.edu.ynnu.service.UserService;

@Controller
public class SystemController {
	@Autowired
	private TitleService titleService;

	@Autowired
	private UserService userService;

	@Autowired
	private FLService flService;

	@Autowired
	private MxService mxService;

	@RequestMapping("/system")
	public String SystemPage(HttpServletRequest request) {
		return "back-stage/system";
	}

	@RequestMapping(value = "/title")
	public String titlePage(Model model) {
		int count = titleService.Count();
		if (count > 0) {
			List<webTitle> webTitles = titleService.getAll();
			model.addAttribute("List", webTitles);
			model.addAttribute("count", count);
		}
		return "back-stage/title/title";
	}

	@RequestMapping(value = "/user")
	public String UserPage(Model model) {
		int count = userService.Count();
		if (count > 0) {
			List<yh> yhList = userService.findAll();
			model.addAttribute("List", yhList);
			model.addAttribute("count", count);
		}
		return "back-stage/user/user";
	}

	@RequestMapping(value = "/classify")
	public String ClassifyPage(Model model) {
		int count = flService.count();
		if (count > 0) {
			List<fl> list = flService.findAll();
			model.addAttribute("List", list);
			model.addAttribute("count", count);
		}
		return "back-stage/classify/classify";
	}

	@RequestMapping(value = "/model")
	public String ModelPage(Model model) {
		int count = mxService.count();
		if (count > 0) {
			List<mx> list = mxService.findAll();
			model.addAttribute("List", list);
			model.addAttribute("count", count);
		}
		return "back-stage/model/model";
	}
}
