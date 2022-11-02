package Cliente_Servidor_Rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Cliente{
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection() ) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("rabbitmq_queue", false, false, false, null);

            Integer message =  100 ;

            channel.basicPublish("","rabbitmq_queue",false,null,message.toString().getBytes());

            System.out.println("!!! mensagem enviada");
        }
    }

}