package cn.edu.ynnu.Controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.ynnu.service.FLService;
import cn.edu.ynnu.service.MxService;
import cn.edu.ynnu.service.TitleService;
import cn.edu.ynnu.service.UserService;

@Controller
public class DeleteController {
	@Autowired
	private TitleService titleService;
	@Autowired
	private UserService userService;

	@Autowired
	private FLService flService;

	@Autowired
	private MxService mxService;

	@RequestMapping(value = "/title-delete")
	public String deleteTitle(@RequestParam Integer titleId) throws SQLException {
		if (titleService.Count() > 0) {
			titleService.delete(titleId);
		}
		return "redirect:/title";
	}

	@RequestMapping(value = "/user-delete")
	public String Userdelete(@RequestParam Integer userId) {
		if (userService.Count() > 0) {
			userService.delete(userId);
		}
		return "redirect:/user";
	}

	@RequestMapping(value = "/fl-delete")
	public String Fldelete(@RequestParam Integer flId) {
		if (flService.count() > 0) {
			flService.delete(flId);
		}
		return "redirect:/classify";
	}

	@RequestMapping(value = "/model-delete")
	public String Modeldelete(@RequestParam int mxId) {
		if (mxService.count() > 0) {
			mxService.delete(mxId);
		}
		return "redirect:/model";
	}

	@RequestMapping(value = "/usermodel-delete")
	public String modeldel(@RequestParam int mxid) {
		mxService.delete(mxid);
		return "redirect:/mymodel";
	}
}
