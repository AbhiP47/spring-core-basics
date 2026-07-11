package com.abhinav.javaBase;


import com.abhinav.jarDependency.CartService;
import com.abhinav.javaBase.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBaseApplication {

	public static void main(String[] args) throws NoSuchFieldException {

		// Application context represents the IOC container
		// Here we are implementing the ApplicationContext interface using the Anno...Config..() which tells spring we
		// want to start a spring IOC container using annotation based configuration
		/*
		passing the reflection or meta-data of the APPConfig class
		 */
		/*
		Steps for application context(IOC Container) flow :
		1. Spring starts the container.
		2. Spring reads app config.java.
		3. Spring processes and adds the root component scan.
		4. Spring finds add component classes.
		5. Spring creates bean definitions.
		6. Spring starts creating objects.
		7. Our application uses the object.


		When you annotate a class with @Component or define a @Bean in a configuration class, Spring doesn’t just instantly create the object.
		 First, it reads your configuration and translates it into an internal data structure called BeanDefinition (specifically,
		  implementations of the org.springframework.beans.factory.config.BeanDefinition interface).
		  This metadata contains answers to the following questions for Spring:
		  What is the actual class? (The full binary class name, e.g., com.example.Student)
		  How should it be scoped? (Is it a singleton, prototype, request, or session?)
		  How should it be created? (Constructor arguments, factory method name, or factory bean)
		   What are its dependencies? (The other beans that need to be injected into it)
		   When should it be initialized? (Is it initialized immediately at startup, or lazy-init?)
		   Are there lifecycle hooks? (Initialization methods like @PostConstruct or destruction methods like @PreDestroy)
				 */
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Get the bean from the IOC Container
		OrderService order = context.getBean(OrderService.class);
		order.placeOrder();
//		PaymentService paymentService = context.getBean(PaymentService.class);
//		paymentService.pay();



//		PaymentService service = new PaymentService();
//
//		OrderService order = new OrderService();
//		order.placeOrder();
//
//		Student s1 = new Student();
//
//		// When you write Class<Student> c1 = Student.class;, you are capturing the metadata of the Student class in c1.
//		// You can use c1 to find out everything about the Student class at runtime—its methods, constructors, fields, and superclasses—even if you didn't know them when writing the code.
//		Class<Student> c1 = Student.class;

//		// CartService is a class from the custom created jar file which is added as a dependency to our project so
//		We cannot use the spring's IOC container functionality to create its object and
//		also we cannot add the @Component annotation to it since it is only a read-only file which is present in the compiled code .class format.
//		CartService cartService = new CartService();
//		cartService.addToCart();

		CartService cartService = context.getBean(CartService.class);
		cartService.addToCart();
	}

}
