package al.infnet.edu.br.estoque_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
    public TopicExchange stockExchange() {
        return new TopicExchange("stock.exchange");
    }

    @Bean
    public Queue stockCreatedQueue() {
        return new Queue("stock.created.queue", true);
    }

    @Bean
    public Queue stockUpdatedQueue() {
        return new Queue("stock.updated.queue", true);
    }

    @Bean
    public Queue stockDeletedQueue() {
        return new Queue("stock.deleted.queue", true);
    }

    @Bean
    public Binding stockCreatedBinding() {
        return BindingBuilder.bind(stockCreatedQueue()).to(stockExchange()).with("stock.created");
    }

    @Bean
    public Binding stockUpdatedBinding() {
        return BindingBuilder.bind(stockUpdatedQueue()).to(stockExchange()).with("stock.updated");
    }

    @Bean
    public Binding stockDeletedBinding() {
        return BindingBuilder.bind(stockDeletedQueue()).to(stockExchange()).with("stock.deleted");
    }
}
