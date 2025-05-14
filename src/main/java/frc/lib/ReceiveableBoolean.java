package frc.lib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ReceiveableBoolean {
    private final NetworkTableEntry value;

    public ReceiveableBoolean(String tableName, String entryName) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable(tableName);
        value = table.getEntry(entryName);
    }

    public boolean getValue() {
        return value.getBoolean(false);
    }
}
