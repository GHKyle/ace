package wiki.chenxun.ace.examples.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.chenxun.ace.core.base.annotations.AceService;
import wiki.chenxun.ace.core.base.annotations.Get;
import wiki.chenxun.ace.core.base.annotations.Post;
import wiki.chenxun.ace.core.base.annotations.RequestParam;
import wiki.chenxun.ace.core.base.common.AceLoggerConfig;
import wiki.chenxun.ace.core.base.config.ConfigBeanParser;
import wiki.chenxun.ace.core.base.config.DefaultConfig;
import wiki.chenxun.ace.core.base.logger.Logger;
import wiki.chenxun.ace.core.base.logger.LoggerFactory;

/**
 * @Description: Created by chenxun on 2017/4/8.
 */
@AceService(path = "/person")
@Component
public class PersonServiceImpl implements PersonService {

    /**
     * 配置
     */
    @Autowired
    private PersonProperties personProperties;

    @Post
    @Override
    public Person test(@RequestParam String name, @RequestParam int age) {
        ConfigBeanParser parser = DefaultConfig.INSTANCE.configBeanParser(AceLoggerConfig.class);
        AceLoggerConfig loggerConfig = new AceLoggerConfig();
        parser.notifyObservers(loggerConfig);
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        return person;
    }

    @Get
    @Override
    public Person test2() {
        Logger logger = LoggerFactory.getLogger("");
        logger.info("test!");
        try {
            Thread.currentThread().sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder("name");
        for (int i = 0; i < 1024; i++) {
            stringBuilder.append("x");
        }
        Person person = new Person();
        person.setAge(personProperties.getAge());
        person.setName(stringBuilder.toString());
        return person;
    }
}
