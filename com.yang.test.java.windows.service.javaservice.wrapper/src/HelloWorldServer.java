import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloWorldServer implements Runnable {
	private final Socket m_socket;
	private final int m_num;

	HelloWorldServer(Socket socket, int num) {
		m_socket = socket;
		m_num = num;

		Thread handler = new Thread(this, "handler-" + m_num);
		handler.start();
	}

	public void run() {
		try {
			try {
				System.out.println(m_num + " Connected.");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						m_socket.getInputStream()));
				OutputStreamWriter out = new OutputStreamWriter(
						m_socket.getOutputStream());
				out.write("Welcome connection #" + m_num + "\n\r");
				out.flush();

				while (true) {
					String line = in.readLine();
					if (line == null) {
						System.out.println(m_num + " Closed.");
						return;
					} else {
						System.out.println(m_num + " Read: " + line);
						if (line.equals("exit")) {
							System.out.println(m_num + " Closing Connection.");
							return;
						}
						// else if ( line.equals( "crash" ) )
						// {
						// System.out.println( m_num +
						// " Simulating a crash of the Server..." );
						// Runtime.getRuntime().halt(0);
						// }
						else {
							System.out.println(m_num + " Write: echo " + line);
							out.write("echo " + line + "\n\r");
							out.flush();
						}
					}
				}
			} finally {
				m_socket.close();
			}
		} catch (IOException e) {
			System.out.println(m_num + " Error: " + e.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 9000;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		System.out.println("Accepting connections on port: " + port);
		int nextNum = 1;
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			HelloWorldServer hw = new HelloWorldServer(socket, nextNum++);
		}
	}
}