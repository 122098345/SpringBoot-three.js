package cn.edu.ynnu.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.ynnu.model.fl;
import cn.edu.ynnu.model.mx;
import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.service.FLService;
import cn.edu.ynnu.service.MxService;
import cn.edu.ynnu.service.TitleService;

@org.springframework.stereotype.Controller
public class IndexController {

	@Autowired
	private TitleService titleService;

	@Autowired
	private FLService flService;

	@Autowired
	private MxService mxService;

	@GetMapping("/")
	public String redirectPage() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(Model model, HttpServletRequest request) {
		List<webTitle> list = titleService.getAll();
		model.addAttribute("TitleList", list);
		return "index";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String ProductsPage(Model model) {
			List<webTitle> list1 = titleService.getAll();
			List<fl> list2 = flService.findAll();
			List<mx> list3 = mxService.findAll();
			model.addAttribute("TitleList", list1);
			model.addAttribute("FlList", list2);
			model.addAttribute("MxList", list3);
		return "products";
	}
	@RequestMapping(value ="/products-fl",method = RequestMethod.GET)
	public String ProductsFlPage(Model model ,@RequestParam String fl) {

		
		if(fl.equals("未分类")) {
			List<mx> list3 = mxService.findAll();
			model.addAttribute("MxList", list3);
		}else {
			List<mx> list4 = mxService.findByFl(fl);
			model.addAttribute("MxList", list4);
		}
		List<webTitle> list1 = titleService.getAll();
		List<fl> list2 = flService.findAll();
		model.addAttribute("TitleList", list1);
		model.addAttribute("FlList", list2);
		
		return "products";
	}
	
	@RequestMapping(value = "/mymodel",method = RequestMethod.GET)
	public String myModelPage(Model model,HttpServletRequest request,HttpSession session) {
		session = request.getSession();
		int yhId =(Integer) session.getAttribute("yh_id");

		List<webTitle> list1 = titleService.getAll();
		List<fl> list2 = flService.findAll();
		List<mx> list3 = mxService.findByYhId(yhId);
		
		model.addAttribute("TitleList", list1);
		model.addAttribute("FlList", list2);
		model.addAttribute("mymodel", list3);
		
		return "mymodel";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String ErrorPage(Model model) {
		List<webTitle> list = titleService.getAll();
		model.addAttribute("TitleList", list);
		return "404";
	}
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String accountPage(Model model) {
		List<webTitle> list = titleService.getAll();
		model.addAttribute("TitleList", list);
		return "account";
	}

	@RequestMapping(value = "/3D")
	public String ThreeJsPage(Model model) {
		return "3D";
	}

	
	@RequestMapping(value = "/ck3D")
	public String ck3DPage(Model model, @RequestParam int id) {
		Optional<mx> mx = mxService.findById(id);
		String json = mx.get().getMx_json();

		model.addAttribute("mxjson", json);
		model.addAttribute("mxid", id);
		return "3D";
	}
}
