package com.example.demo.models;

public class PolarNumber {

	private double radius;
	private double angle;
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngleDegree()
	{
		return this.angle*180/Math.PI;
	}
	
}
