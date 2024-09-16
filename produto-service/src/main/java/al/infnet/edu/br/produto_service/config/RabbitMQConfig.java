package al.infnet.edu.br.produto_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public TopicExchange productExchange() {
        return ExchangeBuilder.topicExchange("product.exchange").durable(true).build();
    }

    @Bean
    public Queue productCreatedQueue() {
        return new Queue("product.created.queue", true);
    }

    @Bean
    public Queue productUpdatedQueue() {
        return new Queue("product.updated.queue", true);
    }

    @Bean
    public Queue productDeletedQueue() {
        return new Queue("product.deleted.queue", true);
    }

    @Bean
    public Binding productCreatedBinding() {
        return BindingBuilder.bind(productCreatedQueue()).to(productExchange()).with("product.created");
    }

    @Bean
    public Binding productUpdatedBinding() {
        return BindingBuilder.bind(productUpdatedQueue()).to(productExchange()).with("product.updated");
    }

    @Bean
    public Binding productDeletedBinding() {
        return BindingBuilder.bind(productDeletedQueue()).to(productExchange()).with("product.deleted");
    }
}
