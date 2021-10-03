package lab.imHarish03.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lab.imHarish03.rabbitmq.config.MessagingConfig;
import lab.imHarish03.rabbitmq.dto.OrderStatus;

@Component
public class User {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Messgae recevived from queue: " + orderStatus);
	}

}
