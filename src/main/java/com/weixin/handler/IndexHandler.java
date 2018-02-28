package com.weixin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.util.ResourcesUtil;

@Controller
public class IndexHandler {

	@RequestMapping("/index")
	public String index() {
		return "login";
	}
}
