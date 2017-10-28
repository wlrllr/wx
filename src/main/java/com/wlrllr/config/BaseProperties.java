package com.wlrllr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信用配置
 *
 * @author wlrllr
 *
 */
@Configuration
@PropertySource("classpath:base.properties")
public class BaseProperties {

	@Value("${jdbc.maxPageSize}")
	private Integer maxPageSize;

	public Integer getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(Integer maxPageSize) {
		this.maxPageSize = maxPageSize;
	}
}
