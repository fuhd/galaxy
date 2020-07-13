package com.dfire.config;

/**
 *
 * @author xiaosuda
 * @date 2018/7/26
 */
//@Configuration
public class DruidConfig {


   /* @Bean
    @Primary
    @ConfigurationProperties("druid.datasource")
    public DataSource druidDateSource() {
        return new DruidDataSource();
    }


    @Bean
    @Primary
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername","hera");
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
*/

   /* @Bean
    @Primary
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }*/
}
