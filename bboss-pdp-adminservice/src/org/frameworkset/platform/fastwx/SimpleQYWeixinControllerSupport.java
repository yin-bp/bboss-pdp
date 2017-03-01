package org.frameworkset.platform.fastwx;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.company.handle.QYEventHandle;
import com.github.sd4324530.fastweixin.company.handle.QYMessageHandle;
import com.github.sd4324530.fastweixin.company.message.req.QYBaseEvent;
import com.github.sd4324530.fastweixin.company.message.req.QYBaseReqMsg;
import com.github.sd4324530.fastweixin.company.message.req.QYEnterAgentEvent;
import com.github.sd4324530.fastweixin.company.message.req.QYImageReqMsg;
import com.github.sd4324530.fastweixin.company.message.req.QYLocationReqMsg;
import com.github.sd4324530.fastweixin.company.message.req.QYMenuEvent;
import com.github.sd4324530.fastweixin.company.message.req.QYScanCodeEvent;
import com.github.sd4324530.fastweixin.company.message.req.QYSendPicInfoEvent;
import com.github.sd4324530.fastweixin.company.message.req.QYTextReqMsg;
import com.github.sd4324530.fastweixin.company.message.req.QYVideoReqMsg;
import com.github.sd4324530.fastweixin.company.message.req.QYVoiceReqMsg;
import com.github.sd4324530.fastweixin.company.message.resp.QYBaseRespMsg;
import com.github.sd4324530.fastweixin.servlet.QYWeixinControllerSupport;

/**
 * 必须自己实现里头的方法
 * @author 1
 *
 */
public class SimpleQYWeixinControllerSupport extends QYWeixinControllerSupport {
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(SimpleQYWeixinControllerSupport.class);
     private static final String TOKEN = "myToken";
	@Override
	protected String getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCropId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getAESKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<QYMessageHandle> initMessageHandles() {
		// TODO Auto-generated method stub
		return super.initMessageHandles();
	}

	@Override
	protected List<QYEventHandle> initEventHandles() {
		// TODO Auto-generated method stub
		return super.initEventHandles();
	}

	@Override
	protected QYBaseRespMsg handleTextMsg(QYTextReqMsg msg) {
		 String content = msg.getContent();
         log.debug("用户发送到服务器的内容:{}", content);
         QYBaseRespMsg msgres = new QYBaseRespMsg();
         msgres.setCreateTime((int)System.currentTimeMillis());
         msgres.setFromUserName(msg.getToUserName());
         msgres.setToUserName(msg.getFromUserName());
         return msgres;
	}

	@Override
	protected QYBaseRespMsg handleImageMsg(QYImageReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleImageMsg(msg);
	}

	@Override
	protected QYBaseRespMsg handleVoiceMsg(QYVoiceReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleVoiceMsg(msg);
	}

	@Override
	protected QYBaseRespMsg handleVideoMsg(QYVideoReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleVideoMsg(msg);
	}

	@Override
	protected QYBaseRespMsg handleShortVideoMsg(QYVideoReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleShortVideoMsg(msg);
	}

	@Override
	protected QYBaseRespMsg handleLocationMsg(QYLocationReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleLocationMsg(msg);
	}

	@Override
	protected QYBaseRespMsg handleMenuClickEvent(QYMenuEvent event) {
		// TODO Auto-generated method stub
		return super.handleMenuClickEvent(event);
	}

	@Override
	protected QYBaseRespMsg handleMenuViewEvent(QYMenuEvent event) {
		// TODO Auto-generated method stub
		return super.handleMenuViewEvent(event);
	}

	@Override
	protected QYBaseRespMsg handleScanCodeEvent(QYScanCodeEvent event) {
		// TODO Auto-generated method stub
		return super.handleScanCodeEvent(event);
	}

	@Override
	protected QYBaseRespMsg handleSendPicsInfoEvent(QYSendPicInfoEvent event) {
		// TODO Auto-generated method stub
		return super.handleSendPicsInfoEvent(event);
	}

	@Override
	protected QYBaseRespMsg handleEnterAgentEvent(QYEnterAgentEvent event) {
		// TODO Auto-generated method stub
		return super.handleEnterAgentEvent(event);
	}

	@Override
	protected QYBaseRespMsg handleSubScribe(QYBaseEvent event) {
		// TODO Auto-generated method stub
		return super.handleSubScribe(event);
	}

	@Override
	protected QYBaseRespMsg handleUnsubscribe(QYBaseEvent event) {
		// TODO Auto-generated method stub
		return super.handleUnsubscribe(event);
	}

	@Override
	protected QYBaseRespMsg handleDefaultMsg(QYBaseReqMsg msg) {
		// TODO Auto-generated method stub
		return super.handleDefaultMsg(msg);
	}

}
