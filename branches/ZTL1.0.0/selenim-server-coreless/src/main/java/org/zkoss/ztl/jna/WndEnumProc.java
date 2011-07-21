package org.zkoss.ztl.jna;


import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public interface WndEnumProc extends StdCallLibrary.StdCallCallback {
    boolean callback (HWND hWnd, int lParam);
}