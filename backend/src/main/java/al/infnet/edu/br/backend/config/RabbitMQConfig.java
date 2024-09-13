package al.infnet.edu.br.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public AmqpTemplate amqpTemplate() {
        return new RabbitTemplate();
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
    public Queue stockUpdatedQueue() {
        return new Queue("stock.updated.queue", true);
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
    public Binding stockUpdatedBinding() {
        return BindingBuilder.bind(stockUpdatedQueue()).to(productExchange()).with("stock.updated");
    }
}
