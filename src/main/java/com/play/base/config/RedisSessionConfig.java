package com.play.base.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 2018/3/31.
 * 分布式Session一致性
 */
@Component
//一分钟失效maxInactiveIntervalInSeconds = 60，默认是180，3分钟
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisSessionConfig {
}
