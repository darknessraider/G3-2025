package frc.lib;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Units;

public class SendablePose2d {
    final NetworkTableEntry xEntry;
    final NetworkTableEntry yEntry;
    final NetworkTableEntry thetaEntry;

    public SendablePose2d(String tableName, String entryName) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable(tableName);
        xEntry = table.getEntry(entryName + "_x");
        yEntry = table.getEntry(entryName + "_y");
        thetaEntry = table.getEntry(entryName + "_theta");
    }

    public void setPose(Pose2d pose) {
        xEntry.setDouble(pose.getMeasureX().in(Units.Inches));
        yEntry.setDouble(pose.getMeasureY().in(Units.Inches));
        thetaEntry.setDouble(pose.getRotation().getDegrees());
    }
}
