package com.spring.app.subscript;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.app.payment.PaymentResultVO;
import com.spring.app.payment.PaymentService;
import com.spring.app.user.MemberStateVO;
import com.spring.app.user.UserService;
import com.spring.app.user.UserVO;

@Controller
@RequestMapping("/subscript/*")
public class SubscriptController {
	
	@Value("${toss.client.api.key}")
	private String tossClientKey;
	
	@Autowired
	private SubscriptService subscriptService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("list")
	public void planList(Model model) throws Exception {
		List<SubscriptionVO> list = subscriptService.getPlans();
		
		model.addAttribute("plans", list);
		model.addAttribute("tossClientKey", tossClientKey);
	}
	
	@GetMapping("success")
	public String success(@RequestParam String authKey,
						@RequestParam String customerKey,
						@RequestParam Long subscriptionId, Model model) throws Exception {
		
		paymentService.registerCard(customerKey, authKey);
		
		model.addAttribute("sub", subscriptService.getPlansDetail(subscriptionId));
		
		return "payment/confirm";
	}
	
	@GetMapping("failure")
	public void fail(@RequestParam("code") String errorCode,
					 @RequestParam("message") String message, Model model) throws Exception {
		
		model.addAttribute("errorCode", errorCode);
		model.addAttribute("message", message);
		
	}
	
	@PostMapping("subscribe")
	public String subscribe(@RequestParam String customerKey,
							@RequestParam Long subscriptionId, Model model) throws Exception {
		PaymentResultVO result = paymentService.approve(customerKey, subscriptionId);
		model.addAttribute("payment", result);
		
		return "payment/result";
	}
	
	@GetMapping("cancel")
	public void cancel(@AuthenticationPrincipal UserVO userVO, Model model) throws Exception{
		model.addAttribute("user", userVO);
	}
	
	@PostMapping("cancel")
	public String cancel(@RequestParam String username) throws Exception {
		MemberStateVO memberStateVO=userService.checkSubscript(username);
		
		userService.cancelSubscript(memberStateVO);
		
		return "redirect:/";
	}
	
	@GetMapping("restart")
	public void reStart(@AuthenticationPrincipal UserVO userVO, Model model) throws Exception {
		LocalDate endDate = subscriptService.getEndDate(userVO.getUsername());
		
		model.addAttribute("endDate", endDate);
		model.addAttribute("user", userVO);
	}
	
	@PostMapping("restart")
	public String reStart(@RequestParam String username) throws Exception {
		MemberStateVO memberStateVO=userService.checkSubscript(username);
		
		userService.startSubscript(memberStateVO);
		
		return "redirect:/";
	}
}
