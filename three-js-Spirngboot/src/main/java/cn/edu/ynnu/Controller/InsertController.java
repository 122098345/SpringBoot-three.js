package cn.edu.ynnu.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ynnu.model.fl;
import cn.edu.ynnu.model.mx;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.service.FLService;
import cn.edu.ynnu.service.MxService;
import cn.edu.ynnu.service.TitleService;
import net.minidev.json.JSONObject;

@Controller
public class InsertController {
	static JSONObject json;
	HttpSession session;
	@Autowired
	private TitleService titleService;

	@Autowired
	private FLService flservice;

	@Autowired
	private MxService mxService;

	@RequestMapping(value = "/title-insert")
	public String TitleInsertPage() {
		return "back-stage/title/insert";
	}

	@RequestMapping(value = "/user-insert")
	public String UserInsertPage() {
		return "back-stage/user/insert";
	}

	@RequestMapping(value = "/fl-insert")
	public String FlInsterPage() {
		return "back-stage/classify/insert";
	}

	@RequestMapping(value = "/title-submit")
	public String TilteSubPage(@RequestParam String title, @RequestParam String date, @RequestParam String level,
			@RequestParam String content, @RequestParam String author, Model model, HttpServletRequest request) {
		session = request.getSession();
		if (!title.isEmpty() && !(date == null) && !level.isEmpty() && !author.isEmpty()) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Date fDate = new Date();
			try {
				fDate = format.parse(date);
				webTitle webTitle = new webTitle();
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
			return "redirect:/title-insert";
		}
		return "redirect:/title";
	}

	@RequestMapping(value = "fl-submit")
	public String FlSubPage(Model model, @RequestParam String mc, @RequestParam String nr) {
		if (!mc.isEmpty()) {
			fl fl = new fl();
			fl.setFlmc(mc);
			fl.setFl_nr(nr);

			flservice.save(fl);
			return "redirect:/classify";
		} else {
			model.addAttribute("error", false);
			return "redirect:/fl-insert";
		}
	}

	// 3D 模型数据
	@ResponseBody
	@RequestMapping(value = "/getModel", method = RequestMethod.POST, produces = "application/json")
	public String ThreeJsModelPage(Model model, @RequestBody JSONObject jsonParam) {
		json = jsonParam;
		return "xjmx";
	}

	@RequestMapping(value = "/xjmx")
	public String ThreeJsModelPage(Model model) {

		List<webTitle> list = titleService.getAll();
		List<fl> flList = flservice.findAll();

		model.addAttribute("TitleList", list);
		model.addAttribute("flList", flList);

		return "xjmx";
	}

	@RequestMapping(value = "/mxsub", method = RequestMethod.POST)
	public String mxsubPage(Model model, HttpServletRequest request, HttpSession session, @RequestParam String yhm,
			@RequestParam String bt, @RequestParam String fl, @RequestParam String date) {
		mx creatmol = new mx();
		session = request.getSession();

		creatmol.setYh_id((Integer) session.getAttribute("yh_id"));
		creatmol.setMx_yhm(yhm);
		creatmol.setMx_bt(bt);
		creatmol.setMx_fl(fl);
		creatmol.setMx_date(date);
		creatmol.setMx_json(json.toJSONString());

		mxService.save(creatmol);

		return "redirect:/products";
	}
}
