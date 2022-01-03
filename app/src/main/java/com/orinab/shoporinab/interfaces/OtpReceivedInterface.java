package com.orinab.shoporinab.interfaces;

public interface OtpReceivedInterface {
  void onOtpReceived(String otp);
  void onOtpTimeout();
}