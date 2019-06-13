package com.supercode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * Creates Redis connection factory
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        logger.debug("Returning bean of JedisConnectionFactory");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisSerializer redisStringSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisCF) {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisCF);

        template.setHashKeySerializer(new GenericToStringSerializer<>(UUID.class));
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setKeySerializer(new GenericToStringSerializer<>(UUID.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.afterPropertiesSet();
        return template;
    }

    /*@Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher(RedisTemplate redisTemplate) {
        return new RedisMessagePublisher(redisTemplate, topic());
    }*/

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("payments");
    }
}
