package org.zkoss.ztl.jna;
import com.sun.jna.Native;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.win32.W32APIOptions;

public interface User32Extra extends User32 {

public User32Extra INSTANCE = (User32Extra) Native.loadLibrary("user32", User32Extra.class, W32APIOptions.DEFAULT_OPTIONS);

boolean ShowWindow(HWND hWnd, int nCmdShow);
boolean SetForegroundWindow(HWND hWnd);
HWND FindWindow(String winClass, String title);
HWND GetForegroundWindow();
int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
boolean GetWindowRect(HWND hWnd, RECT r);
boolean GetClientRect(HWND hWnd, RECT r);

boolean EnumWindows(WndEnumProc wndenumproc, int lParam);
}
