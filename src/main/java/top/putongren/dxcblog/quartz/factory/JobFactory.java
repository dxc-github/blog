package top.putongren.dxcblog.quartz.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * @ClassName: JobFactory
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public class JobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     *Job对象的实例化过程是在Quartz中进行的,这时候我们想要将spring管理的bean注入进来是不行的，
     * 因此需要通过调用父类 AdaptableJobFactory 的方法来实现对Job的实例化，在Job实例化完以后，
     * 再调用自身方法为创建好的Job实例进行属性自动装配并将其纳入到Spring容器的管理之中
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
