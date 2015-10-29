package solutions.graviton.swingoflife.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.tapestry5.ioc.IOCUtilities;
import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;

import solutions.graviton.swingoflife.services.AppModule;
import solutions.graviton.swingoflife.services.Properties;

public class App
{
	private JFrame frame;

	private final Registry registry;

	private final Properties properties;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				try
				{
					RegistryBuilder regBuilder = new RegistryBuilder();
					regBuilder.add(AppModule.class);
					IOCUtilities.addDefaultModules(regBuilder);
					final Registry registry = regBuilder.build(); 
					registry.performRegistryStartup();
					
					App window = new App(registry);
					window.frame.setVisible(true);

					// Cleanup
					// registry.cleanupThread();
					// Shutdown registry
					// registry.shutdown();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public App(final Registry registry)
	{
		this.registry = registry;
		this.properties = registry.getService(Properties.class);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		String title = properties.getProperty("app.title");
		frame = new JFrame(title);
		frame.getContentPane().add(BorderLayout.CENTER, new MainGUI(registry));
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
