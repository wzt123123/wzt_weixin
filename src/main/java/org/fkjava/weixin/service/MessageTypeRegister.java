package org.fkjava.weixin.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.domain.event.EventInMessage;
import org.fkjava.weixin.domain.text.TextInMessage;

/**
 * 负责注册消息类型，把MsgType和对应的class关联起来
 * 
 * @author lwq
 *
 */
public class MessageTypeRegister {

	// 使用一个Map来记录类型和类之间的关系
	private static Map<String, Class<? extends InMessage>> typeMap = new ConcurrentHashMap<>();

	static {
		// 使用静态代码块直接完成类型和类之间的映射
		register("text", TextInMessage.class);
		register("event", EventInMessage.class);
		register("location", TextInMessage.class);
		register("image", TextInMessage.class);
		register("video", TextInMessage.class);
		register("shortvideo", TextInMessage.class);
		register("voice", TextInMessage.class);
	}

	/**
	 * 
	 * @param type 消息类型，比如是text、location、event
	 * @param cla  消息类型对应的类对象（class）
	 */
	public static void register(String type, Class<? extends InMessage> cla) {
		typeMap.put(type, cla);
	}

	/**
	 * 返回类型对应的类对象
	 * 
	 * @param type
	 * @return
	 */
	public static Class<? extends InMessage> getClass(String type) {
		return typeMap.get(type);
	}
}
