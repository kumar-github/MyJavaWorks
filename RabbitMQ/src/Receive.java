import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Receive {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    //User s = new User();
	  ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        //String message = new String(body, "UTF-8");
        //System.out.println(" [x] Received '" + message + "'");
    	  ByteArrayInputStream b = new ByteArrayInputStream(body);
    	  ObjectInputStream i = new ObjectInputStream(b);
    	  try {
			User s = (User)i.readObject();
			System.out.println(s.getFirstName());
			System.out.println(s.getLastName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
    
    /*Consumer consumer = new DefaultConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);
    
    while(true)
    {
    	//consumer.handleDelivery(arg0, arg1, arg2, arg3);
    }*/
    
    
  }
}