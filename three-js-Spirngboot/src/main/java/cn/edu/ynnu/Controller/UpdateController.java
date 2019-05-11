package cn.edu.ynnu.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.ynnu.model.fl;
import cn.edu.ynnu.model.mx;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.model.yh;
import cn.edu.ynnu.service.FLService;
import cn.edu.ynnu.service.MxService;
import cn.edu.ynnu.service.TitleService;
import cn.edu.ynnu.service.UserService;

@Controller
public class UpdateController {
	HttpSession session;
	@Autowired
	private TitleService titleService;

	@Autowired
	private UserService userService;

	@Autowired
	private FLService flService;

	@Autowired
	private MxService mxServive;

	@RequestMapping(value = "/title-update", method = RequestMethod.GET)
	public String TitleUpdate(@RequestParam int titleId, Model model) {
		Optional<webTitle> optional = titleService.findOneList(titleId);
		model.addAttribute("List", optional.get());
		return "back-stage/title/update";
	}

	@RequestMapping(value = "/user-update", method = RequestMethod.GET)
	public String UserUpdate(@RequestParam int userId, Model model) {
		Optional<yh> optional = userService.findById(userId);
		model.addAttribute("List", optional.get());
		return "back-stage/user/update";
	}

	@RequestMapping(value = "/fl-update", method = RequestMethod.GET)
	public String FlUpdate(@RequestParam int flId, Model model) {
		Optional<fl> optional = flService.findById(flId);
		model.addAttribute("List", optional.get());
		return "back-stage/classify/update";
	}

	@RequestMapping(value = "/model-update", method = RequestMethod.GET)
	public String ModelUpdate(@RequestParam int mxId, Model model) {
		Optional<mx> optional = mxServive.findById(mxId);
		List<fl> fl = flService.findAll();
		model.addAttribute("List", optional.get());
		model.addAttribute("fl", fl);
		return "back-stage/model/update";
	}

	@RequestMapping(value = "/title-updatesub")
	public String TitleUpdateSub(@RequestParam int titleId, @RequestParam String title, @RequestParam String date,
			@RequestParam String level, @RequestParam String content, @RequestParam String author, Model model,
			HttpServletRequest request) {
		session = request.getSession();
		if (!title.isEmpty() && !(date == null) && !level.isEmpty() && !author.isEmpty()) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Date fDate = new Date();
			try {
				fDate = format.parse(date);
				webTitle webTitle = new webTitle();
				webTitle.setT_id(titleId);
				webTitle.setT_title(title);
				webTitle.setT_level(level);
				webTitle.setT_date(fDate);
				webTitle.setT_Content(content);
				webTitle.setCzy_name(session.getAttribute("admin_name").toString());
				webTitle.setCzy_id(Integer.parseInt(session.getAttribute("admin_id").toString()));
				titleService.Insert(webTitle);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("error", false);
			return "redirect:/title-update";
		}
		return "redirect:/title";
	}

	@RequestMapping(value = "/user-updatesub")
	public String UserUpdateSub(@RequestParam int userId, @RequestParam String user, @RequestParam String pass,
			@RequestParam String nc, @RequestParam String phone, @RequestParam String email, @RequestParam String date,
			Model model) {
		if (!user.isEmpty() && !(pass == null) && !nc.isEmpty() && !phone.isEmpty()) {
			yh yh = new yh();
			yh.setYh_id(userId);
			yh.setYh_username(user);
			yh.setYh_email(email);
			yh.setYh_nc(nc);
			yh.setYh_password(pass);
			yh.setYh_phone(phone);
			yh.setYh_zcdate(date);
			userService.save(yh);
		} else {
			return "redirect:/user-update";
		}
		return "redirect:/user";
	}

	@RequestMapping(value = "/fl-updatesub")
	public String FlUpdateSub(@RequestParam int flId, @RequestParam String mc, @RequestParam String nr) {
		if (!mc.trim().isEmpty()) {
			fl fl = new fl();
			fl.setFl_id(flId);
			fl.setFlmc(mc);
			fl.setFl_nr(nr);

			flService.save(fl);
			return "redirect:/classify";
		} else {
			return "redirect:/fl-update";
		}
	}

	@RequestMapping(value = "model-updatesub")
	public String ModelUpdatesub(@RequestParam int mxId, @RequestParam int yhId, @RequestParam String yhm,
			@RequestParam String fl, @RequestParam String bt, @RequestParam String date, @RequestParam String json) {
		if (!bt.trim().isEmpty() && !json.isEmpty() && !date.isEmpty()) {
			mx mx = new mx();
			mx.setMx_id(mxId);
			mx.setYh_id(yhId);
			mx.setMx_yhm(yhm);
			mx.setMx_bt(bt);
			mx.setMx_fl(fl);
			mx.setMx_date(date);
			mx.setMx_json(json);

			mxServive.save(mx);

			return "redirect:/model";
		} else
			return "redirect:/model-update";

	}
}
