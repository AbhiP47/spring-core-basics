package org.abhinav;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        /*
        ConfigurableApplicationContext implements the application context interface and it provides us with some of
         the methods which we canuse to configure our application context, like restarting the IOC container or
          closing the IOC container.
         */
        OrderService orderService = context.getBean(OrderService.class);

        orderService.placeOrder();

        // Testing the destruction callback By closing the IOC container
        context.close();
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

/*
A Spring bean goes through the following phases inside the IoC container

Container Initialization
The Spring IoC container starts and loads configuration metadata (XML, annotations, or Java config).
Bean definitions are registered, and infrastructure components (like processors) are prepared.

Bean Instantiation
The container creates the bean object using a constructor or factory method.
At this stage, the bean exists in memory but dependencies are not yet injected.

Dependency Injection
The container resolves required dependencies from the IoC container.
Dependencies are injected via constructor, setter, or field injection.

Custom Init Method
The custom init() method is called once all dependencies are injected.
It is used to perform additional setup like initializing resources, validating properties, or starting connections.

Custom Utility Method
A Custom Utility Method is a normal business or helper method defined inside a Spring bean.
The developer must manually call it through the bean reference.

Destruction
Cleanup logic is executed using @PreDestroy, destroy(), or custom destroy methods.
Resources such as database connections or threads are released before bean removal.
 */

/*
The lifecycle of a Lazy Bean follows the exact same chronological assembly line as an Eager Bean, but with
one massive tactical twist: The entire creation process is completely halted and frozen until someone explicitly
requests the bean at runtime.

If a lazy initialized bean is not requested It only goes through the container initialization and bean instantiation
 lifecycle stages . It will complete its remaining lifecycle when it is requested.

 The Trap Question: "Does a lazy bean call its @PostConstruct method at startup?" Answer: No. Because
 initialization callbacks cannot run until the object is instantiated. Everything is deferred until the runtime trigger.

The "Accidental Awake" Flaw: If an Eager Singleton bean depends on your Lazy bean, and you forget to
 add @Lazy at the injection point inside the eager bean's constructor, your lazy bean will be forced to
  undergo its entire lifecycle at startup anyway. You must use @Lazy at the injection point to force Spring
  to inject a placeholder proxy wrapper instead of waking up the real target bean.

  The Prototype Exception
This entire  flow applies strictly to Lazy Singletons. If a bean is configured as a Prototype,
 it is implicitly lazy by nature, but Spring will completely skip Destruction stage. For prototypes,
 Spring hands the bean over to your application  and abandons it from the IOC Container , leaving its memory cleanup
  entirely to the standard Java Garbage Collector.
 */

/*
Prototype Bean Lifecycle

A Prototype bean fundamentally alters the relationship between the bean and the IoC container.
 It is implicitly lazy at startup, but more importantly, the container abandons the bean halfway through the lifecycle.

Container Initialization: The container starts and registers the prototype bean definition blueprint.
It does not instantiate it at startup.

Bean Instantiation: Every single time the developer requests the bean (e.g., calling context.getBean()),
 the container creates a brand-new bean object using a constructor or factory method.

Dependency Injection: The container resolves and injects required dependencies into this new instance.

Custom Init Method: The custom init() method is called to perform additional setups.

Custom Utility Method: The developer manually calls business or helper methods through the bean reference.

⚠️ The Critical Phase Divergence: Destruction
Destruction (Skipped by Container): The container completely skips this phase for Prototype beans. Once
the Custom Init Method concludes, the Spring IoC container completely relinquishes control and forgets
about the bean instance. It will never automatically execute @PreDestroy, destroy(), or custom destroy methods.

Architectural Consequence: The responsibility for releasing resources and cleaning up memory shifts entirely
 to the developer and the standard Java Garbage Collector.

 Spring treats a Prototype bean like a factory delivery service: it takes your order, manufactures the object,
 wires its dependencies, delivers it to you, and immediately closes the ticket. Cleaning up that object's memory is
 entirely up to you and the standard Java Garbage Collector.

 The Prototype bean lifecycle is designed this way because of a fundamental architectural rule in Spring: The Spring
  container is a manager of singletons, not a garbage collector for short-lived objects.
 */