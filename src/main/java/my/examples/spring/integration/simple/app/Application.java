package my.examples.spring.integration.simple.app;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;


@Configuration
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

		MessageChannel sender = ctx.getBean("messages", MessageChannel.class);
		sender.send(new GenericMessage<String>("Hello world!"));

		PollableChannel reciver = ctx.getBean("messages", PollableChannel.class);
		Message<?> message = reciver.receive();
		System.out.println("Message received: " + message.getPayload());
	}

	@Bean(name = "messages")
	public QueueChannel messages() {
		return new QueueChannel();
	}

}
