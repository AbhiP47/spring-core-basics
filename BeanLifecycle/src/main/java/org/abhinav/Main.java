package org.abhinav;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        OrderService orderService = context.getBean(OrderService.class);

        orderService.placeOrder();
    }
}


    /*
    The 4 Phases of the Bean Lifecycle
    Phase 1: Instantiation (Creation)
    This is where the bean is born. Spring reads your configuration metadata (Annotations or Java Config)
    and uses Java Reflection to call the constructor of your class, creating the raw Java object instance in memory.

    Phase 2: Populate Properties (Dependency Injection)
    Once the object exists, Spring looks at fields or setter methods annotated with @Autowired or @Value.
    It finds the dependencies required by this bean from its container registry and injects them into the newly created instance.

    Phase 3: Initialization (Setup)
    Now that the bean has its dependencies, it goes through an initialization phase to prepare it for work
    (e.g., setting up database connections or validating configurations). This phase contains a few specific hooks:

    Aware Interfaces: If your bean implements interfaces like BeanNameAware or ApplicationContextAware,
    Spring passes the container's metadata to your bean.

    BeanPostProcessor (Before Initialization): Spring executes custom logic before your setup methods run.

    Initialization Custom Methods: Spring runs your custom initialization code. This is where methods annotated
     with @PostConstruct or classes implementing InitializingBean are executed.

    BeanPostProcessor (After Initialization): Spring executes post-initialization logic. This is the exact step
     where Spring wraps your bean in a dynamic AOP proxy (like for @Transactional).

    Phase 4: Ready for Action & Destruction
    The bean is now fully baked and stays alive inside the container to serve your application's requests.

    When the application shuts down (e.g., stopping your Spring Boot application), the Destruction Phase begins.
     Spring gracefully cleans up resources by executing methods annotated with @PreDestroy or classes implementing
     DisposableBean to close open sockets, databases, or file streams.
     */