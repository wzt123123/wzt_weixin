package org.fkjava.weixin.service.impl;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.domain.OUtMessage;
import org.fkjava.weixin.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

// 相当于由Spring帮助开发者执行一个new，创建一个对象。
// 当需要使用的时候，可以直接用@Autowirdd注解，把对象注入到需要使用的地方。
@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired // 自动注入
	@Qualifier("inMessageTemplate") // 根据名字自动注入
	private RedisTemplate<String, ? extends InMessage> inMessageTemplate;

	@Override
	public OUtMessage onMessage(InMessage msg) {
		LOG.trace("转换后的消息对象：\n{}\n", msg);

		// 第一个参数是通道，第二个参数是消息本身。
		// 不同的消息由于处理方式不同，所以建议放入不同的通道中，队列里面可以有近乎无数的通道。
		// 如果多人共享一个服务器，还需要在通道前面增加一个特征。kemao_3_就是特征。
		// msg.getMsgType()把不同类型的消息放入不同通道。
		inMessageTemplate.convertAndSend("wzt_" + msg.getMsgType(), msg);

		// 返回的信息先不管
		return null;
	}
}
