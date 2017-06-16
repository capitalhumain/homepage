package hello;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * https://dzone.com/articles/integrating-quartz-withspring
 *
 * Created by gkatzioura on 16/06/2017.
 */
public class QuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
    private transient AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        final Object object = super.createJobInstance(bundle);
        beanFactory.autowireBean(object);
        return object;
    }

}
