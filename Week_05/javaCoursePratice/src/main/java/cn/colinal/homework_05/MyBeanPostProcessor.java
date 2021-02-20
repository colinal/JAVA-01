package cn.colinal.homework_05;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof XmlBeanAutowiredEntity4){
            System.out.println(beanName+" is created =========================> ");
            XmlBeanAutowiredEntity4 x4 = (XmlBeanAutowiredEntity4)bean;
            x4.setEntityName("entityFour");
        }
        return bean;
    }
}
