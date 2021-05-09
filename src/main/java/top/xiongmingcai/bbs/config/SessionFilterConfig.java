package top.xiongmingcai.bbs.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiongmingcai.bbs.filter.SessionFilter;

@Configuration
public class SessionFilterConfig {

    @Bean
    public SessionFilter sessionFilter() {
        return new SessionFilter();
    }

    @Bean(name = "sessionFilterConf")
    public FilterRegistrationBean initBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(sessionFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("sessionFilterConf");
        return filterRegistrationBean;
    }
}
