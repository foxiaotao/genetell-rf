package com.core.gene.util.alidayu;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 
 * setSmsFreeSignName 签名
 * smsCode 模板中的验证代码
 * recNum 接受者的号码
 * smsTemplateCode 模板的Id
 * author:ranfen
 * date:2017年3月28日 下午4:35:33
 */

public class SMSUtil{

       private static String appkey="23724643";
       private static String appSecret="57aa1e5a126e6d3dd247479af2b0bc58";
       private static String url=" https://eco.taobao.com/router/rest";
       
       public static String sentSmsCode(String smsFreeSignName,String smsCode,String recNum,String smsTemplateCode) throws ApiException{
    	   TaobaoClient client=new DefaultTaobaoClient(url,appkey,appSecret);
    	   AlibabaAliqinFcSmsNumSendRequest req=new AlibabaAliqinFcSmsNumSendRequest();
    	   req.setExtend("");
    	   req.setSmsType("normal");
    	   req.setSmsFreeSignName(smsFreeSignName);
    	   //req.setSmsFreeSignName("小芬测试");
    	   req.setSmsParamString("{code:'"+smsCode+"',product:'"+smsFreeSignName+"'}");
    	   req.setRecNum(recNum);
    	   req.setSmsTemplateCode(smsTemplateCode);
    	   AlibabaAliqinFcSmsNumSendResponse rsp=client.execute(req);	
    	   System.out.print(rsp.getBody());
    	   return rsp.getBody();
       }
  
}
