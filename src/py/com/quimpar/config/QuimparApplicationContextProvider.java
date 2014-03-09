package py.com.quimpar.config;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class QuimparApplicationContextProvider implements ApplicationContextAware {
  
  private static ApplicationContext quimparApplicationContext;
  private Logger log = LoggerFactory.getLogger(getClass());
  
  public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
    log.debug("setting spring context");
    quimparApplicationContext = applicationContext;
  }
  
  public static ApplicationContext getContext() {
    return quimparApplicationContext;
  }
  
}
