package lab.imHarish03.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.imHarish03.rabbitmq.config.MessagingConfig;
import lab.imHarish03.rabbitmq.dto.Order;
import lab.imHarish03.rabbitmq.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping
	public OrderStatus bookOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());

		OrderStatus orderStatus = new OrderStatus(order, "Process", "Order Placed Successfully ");
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHNAGE, MessagingConfig.ROUTINGKEY, orderStatus);

		return orderStatus;
	}
}
