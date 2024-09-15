package al.infnet.edu.br.estoque_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public TopicExchange stockExchange() {
        return new TopicExchange("stock.exchange");
    }

    @Bean
    public Queue stockUpdatedQueue() {
        return new Queue("stock.updated.queue", true);
    }

    @Bean
    public Binding stockUpdatedBinding() {
        return BindingBuilder.bind(stockUpdatedQueue()).to(stockExchange()).with("stock.updated");
    }
}
