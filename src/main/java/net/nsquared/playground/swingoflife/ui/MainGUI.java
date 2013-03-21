package net.nsquared.playground.swingoflife.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import net.nsquared.playground.swingoflife.Universe;
import net.nsquared.playground.swingoflife.services.Properties;

import org.apache.tapestry5.ioc.Registry;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import foxtrot.Job;
import foxtrot.Worker;

@SuppressWarnings("serial")
public class MainGUI extends JPanel {

	private final JButton nextGenerationButton;

	private final JButton startStopButton;

	private final JButton clearButton;

	private final JComboBox patternSelector = new JComboBox();

	private final Timer timer;

	private final Properties properties;

	private Registry registry;

	public MainGUI(final Registry registry) {

		super(new BorderLayout());

		this.registry = registry;

		this.properties = registry.getService(Properties.class);

		final String nextGenerationLabel = properties
				.getProperty("next.generation.label");
		nextGenerationButton = new JButton(nextGenerationLabel);

		final String startLabel = properties.getProperty("start.label");
		startStopButton = new JButton(startLabel);

		final String clearLabel = properties.getProperty("clear.label");
		clearButton = new JButton(clearLabel);

		final Universe grid = new Universe(30, 30, this.registry);
		final UniverseCanvas canvas = new UniverseCanvas(grid);

		JPanel panel = new JPanel(new BorderLayout());

		panel.add(BorderLayout.CENTER, canvas);
		Border etchedBorder = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		Border outerBlankBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border innerBlankBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border = BorderFactory.createCompoundBorder(BorderFactory
				.createCompoundBorder(outerBlankBorder, etchedBorder),
				innerBlankBorder);
		panel.setBorder(border);
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, createControlPanel());

		nextGenerationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.post(new Job() {
					@Override
					public Object run() {
						grid.tick();
						return null;
					}
				});
				canvas.repaint();
			}
		});
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Worker.post(new Job() {
					@Override
					public Object run() {
						grid.deactivateAll();
						return null;
					}
				});
				canvas.repaint();
			}
		});

		ActionListener timerAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Worker.post(new Job() {
					@Override
					public Object run() {
						if (!grid.tick()) {
							stopTimer();
						}
						return null;
					}
				});
				canvas.repaint();
			}
		};
		timer = new Timer(500, timerAction);

		startStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					stopTimer();
				}
				else {
					startTimer();
				}
			}
		});

	}

	private void startTimer() {
		final String stopLabel = properties.getProperty("stop.label");
		startStopButton.setText(stopLabel);
		nextGenerationButton.setEnabled(false);
		clearButton.setEnabled(false);
		patternSelector.setEnabled(false);
		timer.start();
	}

	private void stopTimer() {
		timer.stop();
		final String startLabel = properties.getProperty("start.label");
		startStopButton.setText(startLabel);
		nextGenerationButton.setEnabled(true);
		clearButton.setEnabled(true);
		patternSelector.setEnabled(true);
	}

	private JPanel createControlPanel() {
		FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu:grow",
				"pref, 15dlu, pref, 15dlu, pref, 3dlu:grow, pref");

		PanelBuilder builder = new PanelBuilder(layout);

		CellConstraints cc = new CellConstraints();

		String title = properties.getProperty("app.title");
		builder.addLabel(title, cc.xywh(1, 1, layout.getColumnCount(), 1));

		String info = properties.getProperty("app.description");
		builder.addLabel(info, cc.xywh(1, 3, layout.getColumnCount(), 1));

		JPanel buttonPanel = ButtonBarFactory.buildLeftAlignedBar(
				nextGenerationButton, startStopButton, clearButton);
		builder.add(buttonPanel, cc.xywh(1, 7, layout.getColumnCount(), 1));

		Border etchedBorder = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		Border outerBlankBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border innerBlankBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border = BorderFactory.createCompoundBorder(BorderFactory
				.createCompoundBorder(outerBlankBorder, etchedBorder),
				innerBlankBorder);
		builder.setBorder(border);
		return builder.getPanel();
	}

}
