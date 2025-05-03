package frc.robot.commands;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.ReceiveablePose2d;
import frc.robot.subsystems.drivetrain.Drive;

public class PathFindToGUIPoint extends Command {
        private final Drive m_drive;
        private ReceiveablePose2d receiveablePose;

        public PathFindToGUIPoint(Drive drive) {
                this.m_drive = drive;
                receiveablePose = new ReceiveablePose2d("DrivingGUI", "TargetPose");
                addRequirements(drive);
        }

        @Override
        public void execute() {
                receiveablePose.getPose();
                if (receiveablePose.isChanged()) {
                        Pose2d targetPose = receiveablePose.getPose();
                        PathConstraints constraints = new PathConstraints(3.0, 4.0, Units.degreesToRadians(540),
                                        Units.degreesToRadians(720));

                        AutoBuilder.pathfindToPose(targetPose, constraints).schedule();
                        System.err.println("New Pose!");
                }
        }
}
