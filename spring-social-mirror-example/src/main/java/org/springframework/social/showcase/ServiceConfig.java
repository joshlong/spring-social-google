/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * Main configuration class for the application. Turns on @Component scanning, loads externalized
 * application.properties, and sets up the database.
 *
 * @author Craig Walls
 */
@Configuration
@PropertySource ("classpath:application.properties")
@EnableTransactionManagement
public class ServiceConfig {

	@Bean
	public DataSource dataSource(Environment env) {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(env.getPropertyAsClass("dataSource.driverClass", Driver.class));
		dataSource.setUrl(env.getProperty("dataSource.url").trim());
		dataSource.setUsername(env.getProperty("dataSource.user").trim());
		dataSource.setPassword(env.getProperty("dataSource.password").trim());
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public RequestCache requestCache() {
		return new HttpSessionRequestCache();
	}


}
