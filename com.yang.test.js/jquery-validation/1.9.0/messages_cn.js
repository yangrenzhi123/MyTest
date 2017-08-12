/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
;(function($) {
	$.extend($.validator.messages, {
		required : "请填写信息",
		remote : "请修正该字段",
		email : "请输入正确格式的电子邮件",
		url : "请输入合法的网址",
		date : "请输入合法的日期",
		dateISO : "请输入合法的日期 (ISO).",
		number : "请输入合法的数字",
		digits : "只能输入整数",
		creditcard : "请输入合法的信用卡号",
		equalTo : "请再次输入相同的值",
		accept : "请输入拥有合法后缀名的字符串",
		maxlength : $.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength : $.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength : $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range : $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max : $.validator.format("请输入一个最大为 {0} 的值"),
		min : $.validator.format("请输入一个最小为 {0} 的值"),

	});

	$.validator.setDefaults({
		errorElement : "a"
	});

	$.validator.prototype.showLabel = function(a, b) {
		var d = this.errorsFor(a);
		if (d.length) {
			d.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
			d.attr("generated");
		} else {
			d = $("<" + this.settings.errorElement + "/>").attr({
				"title" : b,
				"for" : this.idOrName(a),
				generated : true
			}).addClass(this.settings.errorClass);
			if (this.settings.wrapper)
				d = d.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
			this.labelContainer.append(d).length || (this.settings.errorPlacement ? this.settings.errorPlacement(d, $(a)) : d.insertAfter(a));
		}
		if (!b && this.settings.success) {
			d.text("");
			typeof this.settings.success == "string" ? d.addClass(this.settings.success) : this.settings.success(d);
		}
		this.toShow = this.toShow.add(d);
	};

	// 手机号码验证
	$.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		var mobile = /^1[3584]\d{9}$/i;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

	// 电话号码验证
	$.validator.addMethod("isTel", function(value, element) {
		var tel = /^((\d{3,4})|\d{3,4}-)?\d{7,8}(-\d+)*$/i;
		//电话号码格式010-12345678
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的电话号码");
	//联系电话(手机/电话皆可)验证
	$.validator.addMethod("isPhone", function(value, element) {
		var mobile = /^1[3584]\d{9}$/i;
		var tel = /^((\d{3,4})|\d{3,4}-)?\d{7,8}(-\d+)*$/i;
		return this.optional(element) || (tel.test(value) || (length == 11 && mobile.test(value)) );
	}, "请正确填写您的联系电话");

	// Email验证
	$.validator.addMethod("isEmail", function(value, element) {
		var reg = /^([a-zA-Z0-9]+[_|\_|\.|-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.|-]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return this.optional(element) || (reg.test(value));
	}, "请正确填写您的电子邮箱");

	$.validator.addMethod("isLoginName", function(value, element) {
		var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){1,20}$/;
		return this.optional(element) || (reg.test(value));
	}, "只能输入2-20个以字母开头、可带数字、“_”、“.”的字符串");

	//英文和数字的验证
	$.validator.addMethod("isEnglishOrShuZi", function(value, element) {
		var reg = /^[0-9a-zA-Z\x00-\xff&]+$/g;
		return this.optional(element) || (reg.test(value));
	}, "请输入数字或者英文!");
	// 验证非纯数字
	$.validator.addMethod("isNAN", function(value, element) {
		var reg = /^\d+$/;
		return this.optional(element) || (reg.test(value));
	}, "用户名不能为纯数字组合");
	// 验证海关编码 十位正整数
	$.validator.addMethod("isCustomCode", function(value, element) {
		var mobile = /^((\d{10}))*$/i;
		return this.optional(element) || (mobile.test(value));
	}, "请正确填写海关编码");
	// 验证数字格式的金额
	$.validator.addMethod("isPriceNum", function(value, element) {
		var mobile = /^[0-9]{1}\d*(\.\d{1,2})?$/;
		return this.optional(element) || (mobile.test(value));
	}, "请正确填写数字格式的金额");
	// 验证汇率
	$.validator.addMethod("isRate", function(value, element) {
		var mobile = /^[0-9]{1}\d*(\.\d{1,9})?$/;
		return this.optional(element) || (mobile.test(value));
	}, "请正确填写数字格式的汇率值");
	
	$.validator.addMethod("notEqual0", function(value, element) {
		var mobile = /^(?!0).*$/;
		return this.optional(element) || (mobile.test(value));
	}, "不能输入0");


})(jQuery); 