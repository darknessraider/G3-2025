package frc.lib;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Units;

public class ReceiveablePose2d {
  NetworkTableEntry xEntry;
  NetworkTableEntry yEntry;
  NetworkTableEntry thetaEntry;
  double previousXEntry;
  double previousYEntry;
  double previousThetaEntry;

  public ReceiveablePose2d(String tableName, String entryName) {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable(tableName);
    xEntry = table.getEntry(entryName + "_x");
    yEntry = table.getEntry(entryName + "_y");
    thetaEntry = table.getEntry(entryName + "_theta");
  }

  public Pose2d getPose() {
    // TODO: Make the defaults be the current robot pose ig
    Translation2d transform = new Translation2d(Units.Inches.of(xEntry.getDouble(0)),
        Units.Inches.of(yEntry.getDouble(0)));
    Rotation2d rotation = new Rotation2d(Units.Degrees.of(thetaEntry.getDouble(0)));

    return new Pose2d(transform, rotation);
  }

  public boolean isChanged() {
    boolean changed = xEntry.getDouble(0) != previousXEntry || yEntry.getDouble(0) != previousYEntry
        || thetaEntry.getDouble(0) != previousThetaEntry;
    previousXEntry = xEntry.getDouble(0);
    previousYEntry = yEntry.getDouble(0);
    previousThetaEntry = thetaEntry.getDouble(0);
    return changed;
  }
}
