package frc.lib;

import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;

public class GUICommands {
    private static GUICommands instance;

    private final NetworkTableEntry guiCommandToRun;
    private final NetworkTableEntry commandActivator;

    private GUICommands () {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("DrivingGUI");
        guiCommandToRun = table.getEntry("command_to_run");
        commandActivator = table.getEntry("command_activator");
    }

    public static synchronized GUICommands getInstance() {
        if (instance == null) instance = new GUICommands();
        return instance;
    }

    public Command getCurrentCommand() {
        return NamedCommands.getCommand(guiCommandToRun.getString(""));
    }

    public String getCurrentCommandName() {
        return guiCommandToRun.getString("");
    }

    public boolean activateCommand() {
        return commandActivator.getBoolean(false);
    }
}
