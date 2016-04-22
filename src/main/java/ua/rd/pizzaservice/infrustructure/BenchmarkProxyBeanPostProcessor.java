package ua.rd.pizzaservice.infrustructure;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.util.ClassUtils;

public class BenchmarkProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Before bean: " + bean.getClass());
		
		final Class type = getRealClassOfBean(bean);
		ProxyForBenchmarkAnnotation pfba = new ProxyForBenchmarkAnnotation(bean) {
			@Override
			protected Class getClazz() {
				return type;
			}
		};
		
		try {
			return pfba.proxyObjForBenchmarkAnnotation();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return bean;
		}
	}

	private Class getRealClassOfBean(Object bean) {
		if (ClassUtils.isCglibProxyClass(bean.getClass())) {
			return getSuperClass(bean);
		} else {
			return bean.getClass();
		}
		
	}
	
	
	private Class getSuperClass(Object bean) {
		return bean.getClass().getSuperclass();
	}




	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
}
