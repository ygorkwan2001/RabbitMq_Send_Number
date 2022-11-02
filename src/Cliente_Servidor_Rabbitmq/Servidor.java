package Cliente_Servidor_Rabbitmq;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Servidor {
    public static void main(String[] argv) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("rabbitmq_queue", false, false, false, null);

        channel.basicConsume("rabbitmq_queue",true,(consumerTag, message)-> {
            String m = new String(message.getBody(), "UTF-8");
            int conversao = Integer.valueOf(m);
            int converter = conversao * 2;
            System.out.println("Acabei de receber uma mensagem = " + converter);
        }, consumerTag -> {});
    }
}

