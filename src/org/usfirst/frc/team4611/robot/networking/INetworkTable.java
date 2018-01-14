package org.usfirst.frc.team4611.robot.networking;

public interface INetworkTable {
	public void updateValue(String key, Object value);
	public void onValueChanged(String key, Object newvalue);
}
