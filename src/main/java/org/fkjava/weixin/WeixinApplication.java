package org.fkjava.weixin;

import org.fkjava.weixin.domain.InMessage;
import org.fkjava.weixin.service.JsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.xml.StaxUtils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@SpringBootApplication
public class WeixinApplication {

	@Bean()
	public XmlMapper xmlMapper() {
		XmlMapper mapper = new XmlMapper(StaxUtils.createDefensiveInputFactory());
		return mapper;
	}

	// RedisTemplate是一个模板，用于访问数据库的！
	@Bean // 把对象放入容器里面
	public RedisTemplate<String, ? extends InMessage> inMessageTemplate(//
			// 获取Redis的连接工厂，这个配置是由Spring Boot自动完成，只需要这里说需要，然后就有了！
			// 为了让Spring Boot能够完成自动化配置，必须有spring.redis前缀的配置参数。
			@Autowired RedisConnectionFactory connectionFactory) {

		RedisTemplate<String, ? extends InMessage> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		// 使用序列化程序完成对象的序列化和反序列化，可以自定义
		// 序列化程序负责Java对象和其他格式的数据相互转换。
		// JSON是一种纯文本的格式，非常方便在网络上传输。
		template.setValueSerializer(new JsonRedisSerializer<InMessage>());

		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeixinApplication.class, args);
	}

}
