import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send
{
	private static final String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws  Exception
	{
		User user = new User();
		user.setFirstName("First Name");
		user.setLastName("Last Name");
		
		//ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("a.dat"));
		//o.writeObject(user);
		
		ByteArrayOutputStream bb = new ByteArrayOutputStream();
		
		ObjectOutputStream o = new ObjectOutputStream(bb);
		o.writeObject(user);
		
		
//		ObjectInputStream i = new ObjectInputStream(new FileInputStream("a.dat"));
//		User newUser = (User)i.readObject();
//		System.out.println(newUser.getFirstName());
//		System.out.println(newUser.getLastName());
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "This is to test.";
			//channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			for(int i=0;i<1000;i++)
			{
				channel.basicPublish("", QUEUE_NAME, null, bb.toByteArray());
			}
			System.out.println("X sent : " + message + "");
			
			channel.close();
			connection.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (TimeoutException e)
		{
			e.printStackTrace();
		}
	}
}