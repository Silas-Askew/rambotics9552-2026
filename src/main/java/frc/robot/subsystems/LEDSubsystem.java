package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// contents copied / adapted almost entirely from 
// https://docs.wpilib.org/en/stable/docs/software/hardware-apis/misc/addressable-leds.html
public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED m_ledStrand;
    private final AddressableLEDBuffer m_ledBuffer;
    private final AddressableLEDBufferView m_leftView;
    private final AddressableLEDBufferView m_rightView;

    // all hues at maximum saturation and half brightness
    private final LEDPattern m_rainbow;

    // Our LED strip has a density of 120 LEDs per meter
    private static final Distance kLedSpacing = Meters.of(1 / 256.0);

    // Create a new pattern that scrolls the rainbow pattern across the LED strip, moving at a speed
    // of 1 meter per second.
    private final LEDPattern m_scrollingRainbow;

//   public void robotPeriodic() {
//     // Update the buffer with the rainbow animation

//     m_scrollingRainbow.applyTo(m_ledBuffer);
//     // Set the LEDs
//     m_ledStrand.setData(m_ledBuffer);
//   }


    public LEDSubsystem() {
        this.m_ledStrand = new AddressableLED(Constants.LED.kLedPort);
        this.m_ledBuffer = new AddressableLEDBuffer(Constants.LED.kLedBufferLength);
        this.m_leftView = this.m_ledBuffer.createView(0, 255);
        this.m_rightView = this.m_ledBuffer.createView(256, 511).reversed();
        this.m_ledStrand.setLength(m_ledBuffer.getLength());
        this.m_ledStrand.start();

        this.m_rainbow = LEDPattern.rainbow(255, 128);
        this.m_scrollingRainbow = this.m_rainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(0.33), kLedSpacing);
        // Set the default command to turn the strip off, otherwise the last colors written by
        // the last command to run will continue to be displayed.
        // Note: Other default patterns could be used instead!
        //setDefaultCommand(runPattern(m_scrollingRainbow));
    }



    @Override
    public void periodic() {
        // Periodically send the latest LED color data to the LED strip for it to display
        this.m_scrollingRainbow.applyTo(this.m_ledBuffer);

        this.m_ledStrand.setData(this.m_ledBuffer);
    }

    /**
     * Creates a command that runs a pattern on the entire LED strip.
     *
     * @param pattern the LED pattern to run
     */
    public Command runPattern(LEDPattern pattern) {
        return run(() -> pattern.applyTo(m_ledBuffer));
    }
}
