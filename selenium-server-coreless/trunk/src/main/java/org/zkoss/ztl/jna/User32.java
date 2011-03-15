package org.zkoss.ztl.jna;
import com.sun.jna.Native;

public interface User32 extends W32API {

public User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, DEFAULT_OPTIONS);

boolean ShowWindow(HWND hWnd, int nCmdShow);
boolean SetForegroundWindow(HWND hWnd);
HWND FindWindow(String winClass, String title);
HWND GetForegroundWindow();
int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
boolean GetWindowRect(HWND hWnd, RECT r);
boolean GetClientRect(HWND hWnd, RECT r);

boolean EnumWindows(WndEnumProc wndenumproc, int lParam);
}
