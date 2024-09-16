package al.infnet.edu.br.historico_service.config;

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
    public TopicExchange historicoExchange() {
        return new TopicExchange("historico.exchange");
    }

    @Bean
    public Queue historicoCreatedQueue() {
        return new Queue("historico.created.queue", true);
    }

    @Bean
    public Queue historicoUpdatedQueue() {
        return new Queue("historico.updated.queue", true);
    }

    @Bean
    public Binding historicoCreatedBinding() {
        return BindingBuilder.bind(historicoCreatedQueue()).to(historicoExchange()).with("historico.created");
    }

    @Bean
    public Binding historicoUpdatedBinding() {
        return BindingBuilder.bind(historicoUpdatedQueue()).to(historicoExchange()).with("historico.updated");
    }
}
