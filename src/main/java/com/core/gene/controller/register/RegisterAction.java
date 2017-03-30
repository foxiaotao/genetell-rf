package com.core.gene.controller.register;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.gene.bean.User;
import com.core.gene.bean.alidayu.SmsErrorResponse;
import com.core.gene.bean.alidayu.SmsResponse;
import com.core.gene.bean.alidayu.SmsSuccessSendResponse;
import com.core.gene.bean.alidayu.SmsSuccessSendResponseResult;
import com.core.gene.bean.msg.ReturnBean;
import com.core.gene.service.UserService;
import com.core.gene.session.SessionProvider;
import com.core.gene.util.GenaratorUtil;
import com.core.gene.util.GeneConstant;
import com.core.gene.util.StringUtil;
import com.core.gene.util.TimeUtil;
import com.core.gene.util.alidayu.SMSUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/reg")
public class RegisterAction {
	
	Logger logger = Logger.getLogger(getClass());
	@Autowired
    UserService userServiceImpl;
	@Autowired
	SessionProvider sessionProvider;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	   /**
     * 获取短信验证码
     * @param record
     * @throws Exception
     */
    @RequestMapping(value="/getSmsCode.do")
    public ResponseEntity getSmsCode(HttpServletRequest request, HttpServletResponse response,User record) throws Exception {
    	//验证手机号码不能为空
    	if(record == null || record.getTelephone() == null){
    		return new ResponseEntity(new ReturnBean("注册手机号码不能为空", false), HttpStatus.OK);
    	}else if(!GenaratorUtil.patternTelePhone(record.getTelephone()).matches()){
    		//手机号码 正则匹配 验证
    		return new ResponseEntity(new ReturnBean("请输入正确的手机号码注册", false), HttpStatus.OK);
    	}
    	
    	
    	//同一个ip，用不同号码 无限 请求发送 ??? 怎么办
    	
    	//阿里大于 api 中给出
    	//*****
    	//短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。 短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）。
    	Serializable serDate = sessionProvider.getAttribute(request, response,GeneConstant.getCellphoneSessionRegDateKey(record.getTelephone()) );
    	Date regDate;
    	String sentSmsCodeResult = "";
    	if(serDate!=null){
    		regDate = (Date) serDate;
    		//比较时间
    		long diff = TimeUtil.timeDiffSecond(regDate, new Date());
    		if(diff < 60 && diff > 0){
    			logger.error("【获取短信验证码】两次短信验证码时间间隔不能少于60秒,（单位:秒）timeDiff="+diff);
    			//防止注册时，注册人恶意连续 点 获取短信验证码（前台控制+后台控制 1min之后只能获取一次）
    			return new ResponseEntity(new ReturnBean("60秒内不可重复发送短信验证码", false), HttpStatus.OK);
    		}else if(diff < 600){
    			//同一个手机号码在10分钟之内不能连续发送，如果该手机号码在10min之内发送过短信验证码，那么久不在生成新的短信验证码，用之前的短信验证码，用cellphone作为key，保存在session中
    			Serializable code = sessionProvider.getAttribute(request, response,GeneConstant.getCellphoneSessionContentKey(record.getTelephone()));
    			if(code==null){
    				//考虑session中code丢失的情况
    				code = StringUtil.randomNumber(6);
    			}
    			logger.error("【获取短信验证码】10分钟内发送相同短信验证码，code="+code+",（单位:秒）timeDiff="+diff);
    			sentSmsCodeResult = SMSUtil.sentSmsCode("小涛测试", code.toString(), record.getTelephone(), "SMS_58600016");
    			//更新缓存中 入缓存时间，以便下次进入获取 比较时间
    			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionRegDateKey(record.getTelephone()),new Date());
    			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionContentKey(record.getTelephone()),record.getTelephone());
    			
    		}else if(diff >= 600){
    			//短信发送(超过10min )重新发送
    			String _code = StringUtil.randomNumber(6);
    			logger.error("【获取短信验证码】超过10分钟，重新生成随机短信验证码，code="+_code+",（单位:秒）timeDiff="+diff);
            	sentSmsCodeResult = SMSUtil.sentSmsCode("小涛测试", _code , record.getTelephone(), "SMS_58600016");
            	//更新缓存中 入缓存时间，以便下次进入获取 比较时间
    			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionRegDateKey(record.getTelephone()),new Date());
    			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionContentKey(record.getTelephone()),record.getTelephone());
    		}
    	}else{
    		String _code = StringUtil.randomNumber(6);
    		logger.error("【获取短信验证码】用户首次获取短信验证码，code="+_code);
    		//短信发送
        	sentSmsCodeResult = SMSUtil.sentSmsCode("小涛测试", _code , record.getTelephone(), "SMS_58600016");
        	//更新缓存中 入缓存时间，以便下次进入获取 比较时间
			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionRegDateKey(record.getTelephone()),new Date());
			sessionProvider.setAttribute(request, response,GeneConstant.getCellphoneSessionContentKey(record.getTelephone()),record.getTelephone());
    	}
    	SmsResponse smsResponse = getSmsResponse(sentSmsCodeResult);
    	//将sentSmsCodeResult（json串）转成对象，验证sentSmsCodeResult 中的success是否成功，成功则告诉前台已发送
    	if(smsResponse.isSuccess()){
    		return new ResponseEntity(new ReturnBean("短信发送成功", true), HttpStatus.OK);
    	}else{
    		StringBuffer errorMsg = new StringBuffer("短信发送失败");
    		if(smsResponse.getError_response()!=null){
    			SmsErrorResponse error = (SmsErrorResponse)smsResponse.getError_response();
    			errorMsg.append(",错误码：")
    					.append(error.getSub_code())
    					.append(",错误信息：")
    					.append(error.getSub_msg());
    		}
    		return new ResponseEntity(new ReturnBean(errorMsg.toString(), false), HttpStatus.OK);
    	}
    	//TODO
    	//或者新建sms_code表保存code（code 表的形式）
    }
    
    
    private SmsResponse getSmsResponse(String sentSmsCodeResult) {
		logger.error("【封装阿里大于短信短信验证码返回json数据】json="+sentSmsCodeResult);
		Gson gson = new GsonBuilder().create();
		SmsResponse smsResponse = gson.fromJson(sentSmsCodeResult, SmsResponse.class);
		//发送成功和发送失败返回的是两种格式的json  （在不知道是成功还是失败的情况下，都能将json封装进SmsResponse 对象）
		if(smsResponse.getError_response()!=null){
			//如果有错误信息，封装错误信息 	样式：{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.BUSINESS_LIMIT_CONTROL","sub_msg":"触发业务流控","request_id":"2rxau4yrdk8z"}}
			smsResponse.setError_response(gson.fromJson(smsResponse.getError_response().toString(), SmsErrorResponse.class));
			smsResponse.setSuccess(false);
		}
		if(smsResponse.getAlibaba_aliqin_fc_sms_num_send_response()!=null){
			//发送成功的情况
			//{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"106657213934^1109029712595","success":true},"request_id":"el32x1sed6hk"}}
			smsResponse.setSuccess(true);
			SmsSuccessSendResponse smsSuccessSendResponse = gson.fromJson(smsResponse.getAlibaba_aliqin_fc_sms_num_send_response().toString(), SmsSuccessSendResponse.class);
			if(smsSuccessSendResponse.getResult()!=null){
				//result 有数据才封装到SmsSuccessSendResponse的result属性中，如 json-中的result==null，不进行封装
				smsSuccessSendResponse.setResult(gson.fromJson(smsSuccessSendResponse.getResult().toString(), SmsSuccessSendResponseResult.class));
			}
			smsResponse.setAlibaba_aliqin_fc_sms_num_send_response(smsSuccessSendResponse);
		}
		return smsResponse;
	}

	/**
     * 注册
     * @param record
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/register.do")
    public ResponseEntity register(HttpServletRequest request, HttpServletResponse response,User record,String code) throws Exception {
    	//验证手机号码不能为空
    	//验证手机号码不能为空
    	if(record == null || record.getTelephone() == null){
    		return new ResponseEntity(new ReturnBean("注册手机号码不能为空", false), HttpStatus.OK);
    	}else if(!GenaratorUtil.patternTelePhone(record.getTelephone()).matches()){
    		//手机号码 正则匹配 验证
    		return new ResponseEntity(new ReturnBean("请输入正确的手机号码注册", false), HttpStatus.OK);
    	}else if(record.getUsername() == null){
    		//username
    		return new ResponseEntity(new ReturnBean("用户名不能为空", false), HttpStatus.OK);
    	}else if(record.getPassword() == null){
    		//passwrod
    		return new ResponseEntity(new ReturnBean("密码不能为空", false), HttpStatus.OK);
    	}else if(record.getEmail() == null){
    		//passwrod
    		return new ResponseEntity(new ReturnBean("邮箱不能为空", false), HttpStatus.OK);
    	}else if(!GenaratorUtil.patternEmail(record.getEmail()).matches()){
    		//邮箱 正则匹配 验证
    		return new ResponseEntity(new ReturnBean("请输入正确的邮箱注册", false), HttpStatus.OK);
    	}else if(code == null){
    		//code 页面提交的code
    		return new ResponseEntity(new ReturnBean("短信验证码不能为空", false), HttpStatus.OK);
    	}
    	//缓存中的code
		Serializable codeSession = sessionProvider.getAttribute(request, response,GeneConstant.getCellphoneSessionContentKey(record.getTelephone()) );

		if(code.equals(codeSession)){
			record.setInsertTime(new Date());
	        userServiceImpl.insert(record);
		}else{
			return new ResponseEntity(new ReturnBean("短信验证码错误", false), HttpStatus.OK);
		}
        
        return new ResponseEntity(new ReturnBean("注册成功", true), HttpStatus.OK);
    }
}