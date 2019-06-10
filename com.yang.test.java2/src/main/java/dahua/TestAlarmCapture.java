package dahua;

import java.awt.AWTEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import main.java.com.netsdk.common.Res;
import main.java.com.netsdk.lib.NetSDKLib;
import main.java.com.netsdk.lib.Utils;

public class TestAlarmCapture {
	private DisConnect     disConnect    = new DisConnect();
	private HaveReConnect  haveReConnect = new HaveReConnect();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				TestAlarmCapture testAlarmCapture = new TestAlarmCapture();
				try {
					NetSDKLib netsdk = (NetSDKLib)Native.loadLibrary(Utils.getLoadLibrary("dhnetsdk"), NetSDKLib.class);
					testAlarmCapture.monitorAlarmAndCapture(61803, netsdk);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

//		new Thread(new Runnable() {
//			public void run() {
//				TestAlarmCapture testAlarmCapture = new TestAlarmCapture();
//				try {
//					NetSDKLib netsdk = (NetSDKLib)Native.loadLibrary(Utils.getLoadLibrary("dhnetsdk2"), NetSDKLib.class);
//					testAlarmCapture.monitorAlarmAndCapture(61578, netsdk);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
	}

	private void monitorAlarmAndCapture(int port, NetSDKLib netsdk) throws InterruptedException {
		System.out.println(netsdk.hashCode());
		
		netsdk.CLIENT_Init(disConnect, null);
		NetSDKLib.LOG_SET_PRINT_INFO setLog = new NetSDKLib.LOG_SET_PRINT_INFO();
        File path = new File("./sdklog/");
        if (!path.exists()) {
            path.mkdir();
        }
		String logPath = path.getAbsoluteFile().getParent() + "\\sdklog\\" + System.currentTimeMillis() + ".log";
		setLog.nPrintStrategy = 0;
		setLog.bSetFilePath = 1;
		System.arraycopy(logPath.getBytes(), 0, setLog.szLogFilePath, 0, logPath.getBytes().length);
		System.out.println(logPath);
		setLog.bSetPrintStrategy = 1;
		netsdk.CLIENT_LogOpen(setLog);
		netsdk.CLIENT_SetAutoReconnect(haveReConnect, null);
		int waitTime = 5000; //登录请求响应超时时间设置为5S
		int tryTimes = 1;    //登录时尝试建立链接1次
		netsdk.CLIENT_SetConnectTime(waitTime, tryTimes);
		NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
		netParam.nConnectTime = 10000;      // 登录时尝试建立链接的超时时间
		netParam.nGetConnInfoTime = 3000;   // 设置子连接的超时时间
		netsdk.CLIENT_SetNetworkParam(netParam);
		
		
		
		

		NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();
		IntByReference nError = new IntByReference(0);
		NetSDKLib.LLong m_hLoginHandle = netsdk.CLIENT_LoginEx2("124.160.79.205", port, "admin", "admin123", 0, null, m_stDeviceInfo, nError);

		// 报警
		new Thread(() -> {
			netsdk.CLIENT_SetDVRMessCallBack(cbMessage, null);

			if (!netsdk.CLIENT_StartListenEx(m_hLoginHandle)) {
				System.err.printf("CLIENT_StartListenEx Failed!");
			} else {
				System.out.println("CLIENT_StartListenEx success.");
			}

		}).start();

		

		CountDownLatch latch = new CountDownLatch(1);
//		latch.await(86400, TimeUnit.SECONDS);
		latch.await(3600, TimeUnit.SECONDS);
//		latch.await(120, TimeUnit.SECONDS);
		

		netsdk.CLIENT_Logout(m_hLoginHandle);
		netsdk.CLIENT_LogClose();
		netsdk.CLIENT_Cleanup();
	}

	/////////////////function///////////////////
	// device disconnect callback class
	// set it's instance by call CLIENT_Init, when device disconnect sdk will call it.
	private class DisConnect implements NetSDKLib.fDisConnect {
		public DisConnect() { }

		public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
			System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// frame.setTitle(com.main.java.netsdk.common.Res.string().getAlarmListen() + " : " + com.main.java.netsdk.common.Res.string().getDisConnectReconnecting());
					System.out.println("DisConnect: " + Res.string().getAlarmListen() + " : " + Res.string().getDisConnectReconnecting());
				}
			});
		}
	}

	// device reconnect(success) callback class
	// set it's instance by call CLIENT_SetAutoReconnect, when device reconnect success sdk will call it.
	private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
		public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
			System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// frame.setTitle(com.main.java.netsdk.common.Res.string().getAlarmListen() + " : " + com.main.java.netsdk.common.Res.string().getOnline());
					System.out.println("HaveReConnect: " + Res.string().getAlarmListen() + " : " + Res.string().getOnline());
				}
			});
		}
	}


	private fAlarmDataCB cbMessage = new fAlarmDataCB();
	private class fAlarmDataCB implements NetSDKLib.fMessCallBack{
		public boolean invoke(int lCommand, NetSDKLib.LLong lLoginID, Pointer pStuEvent, int dwBufLen, String strDeviceIP, NativeLong nDevicePort, Pointer dwUser) {
			switch (lCommand) {
				case NetSDKLib.NET_MOTION_ALARM_EX: {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					
					byte []alarm = new byte[dwBufLen];
					pStuEvent.read(0, alarm, 0, dwBufLen);
					if(alarm[0]==1){
						System.out.println(df.format(new Date()) + " " + NetSDKLib.NET_MOTION_ALARM_EX + " ---- 动态报警-开始");
					}else {
						System.out.println(df.format(new Date()) + " " + NetSDKLib.NET_MOTION_ALARM_EX + " ---- 动态报警-结束");
					}
					break;
				}
				case NetSDKLib.EVENT_IVS_FACEDETECT:{ //
					System.out.println(NetSDKLib.EVENT_IVS_FACEDETECT + "---- 人脸检测");
					break;
				}
				case NetSDKLib.EVENT_IVS_FACERECOGNITION:{
					System.out.println(NetSDKLib.EVENT_IVS_FACERECOGNITION + "---- 人脸识别");
					break;
				}
				case NetSDKLib.EVENT_IVS_FACEANALYSIS:{
					System.out.println(NetSDKLib.EVENT_IVS_FACEANALYSIS + "---- 人脸分析");
					break;
				}
				case 8587:{
					System.out.println(8587 + "---- 区域报警");
					break;
				}
				case 8585:{
					System.out.println(8585 + "---- 区域报警");
					break;
				}
				case NetSDKLib.NET_ALARM_ALARM_EX:break;
				case NetSDKLib.NET_VIDEOLOST_ALARM_EX:break;
				case NetSDKLib.NET_SHELTER_ALARM_EX:break;
				case NetSDKLib.NET_DISKFULL_ALARM_EX:break;
				case NetSDKLib.NET_DISKERROR_ALARM_EX:break;
				default:
					break;
			}
			return true;
		}
	}

	// alarm listen event
	class AlarmListenEvent extends AWTEvent {
		private static final long serialVersionUID = 1L;
		public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;

		private AlarmEventInfo alarmEventInfo;

		public AlarmListenEvent(Object target,
								AlarmEventInfo alarmEventInfo) {
			super(target,EVENT_ID);

			this.alarmEventInfo = alarmEventInfo;
			++AlarmEventInfo.index;
			this.alarmEventInfo.id = AlarmEventInfo.index;
		}

		public AlarmEventInfo getAlarmEventInfo() {
			return alarmEventInfo;
		}
	}

	enum AlarmStatus {
		ALARM_START, ALARM_STOP
	}

	// struct of alarm event
	static class AlarmEventInfo {
		public static long        index = 0;
		public        long        id;
		public        int         chn;
		public        int         type;
		public        Date        date;
		public        AlarmStatus status;

		public AlarmEventInfo(int chn, int type, AlarmStatus status) {
			this.chn = chn;
			this.type = type;
			this.status = status;
			this.date = new Date();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			AlarmEventInfo showInfo = (AlarmEventInfo) o;
			return chn == showInfo.chn && type == showInfo.type;
		}
	}
}