package dahua;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import main.java.com.netsdk.lib.NetSDKLib;
import main.java.com.netsdk.lib.NetSDKLib.LLong;
import main.java.com.netsdk.lib.ToolKits;
import main.java.com.netsdk.lib.Utils;

public class TestDahua {

	private static DisConnect disConnect = new DisConnect();
	private static HaveReConnect haveReConnect = new HaveReConnect();
	private CountDownLatch latch = null;

	public static void main(String[] args) throws InterruptedException {
		new TestDahua().doIt();
	}

	public void doIt() throws InterruptedException {
		latch = new CountDownLatch(1);
		NetSDKLib netsdk = (NetSDKLib)Native.loadLibrary(Utils.getLoadLibrary("dhnetsdk"), NetSDKLib.class);
		
		init(disConnect, haveReConnect, netsdk);

		netsdk.CLIENT_SetSnapRevCallBack(m_CaptureReceiveCB, null);
		
		NetSDKLib.LLong m_hLoginHandle = netsdk.CLIENT_LoginEx2("124.160.79.205", 61143, "admin", "admin", 0, null, new NetSDKLib.NET_DEVICEINFO_Ex(), new IntByReference(0));
		NetSDKLib.LLong m_hPlayHandle = netsdk.CLIENT_RealPlayEx(m_hLoginHandle, 0, null, 0);

		remoteCapturePicture(0, netsdk, m_hLoginHandle);
		latch.await(10, TimeUnit.SECONDS);
		
		latch = new CountDownLatch(1);
		remoteCapturePicture(0, netsdk, m_hLoginHandle);
		latch.await(10, TimeUnit.SECONDS);
		
		latch = new CountDownLatch(1);
		remoteCapturePicture(0, netsdk, m_hLoginHandle);
		latch.await(10, TimeUnit.SECONDS);

		netsdk.CLIENT_StopRealPlayEx(m_hPlayHandle);
		netsdk.CLIENT_Logout(m_hLoginHandle);
		netsdk.CLIENT_LogClose();
		netsdk.CLIENT_Cleanup();
	}

	public fCaptureReceiveCB m_CaptureReceiveCB = new fCaptureReceiveCB();
	public class fCaptureReceiveCB implements NetSDKLib.fSnapRev {
		BufferedImage bufferedImage = null;
		public void invoke(LLong lLoginID, Pointer pBuf, int RevLen, int EncodeType, int CmdSerial, Pointer dwUser) {
			if (pBuf != null && RevLen > 0) {
				String strFileName = System.currentTimeMillis() + ".jpg";

				System.out.println("strFileName = " + strFileName);

				byte[] buf = pBuf.getByteArray(0, RevLen);
				ByteArrayInputStream byteArrInput = new ByteArrayInputStream(buf);
				try {
					bufferedImage = ImageIO.read(byteArrInput);
					if (bufferedImage == null) {
						return;
					}
					ImageIO.write(bufferedImage, "jpg", new File(strFileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}
	}

	private static class DisConnect implements NetSDKLib.fDisConnect {
		public void invoke(LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
			System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);
		}
	}

	private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
		public void invoke(LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
			System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);
		}
	}
	
	public boolean init(NetSDKLib.fDisConnect disConnect, NetSDKLib.fHaveReConnect haveReConnect, NetSDKLib netsdk) {

		// sdk 初始化
		netsdk.CLIENT_Init(disConnect, null);

		//打开日志，可选
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

		// 设置断线重连回调接口，设置过断线重连成功回调函数后，当设备出现断线情况，SDK内部会自动进行重连操作
		// 此操作为可选操作，但建议用户进行设置
		netsdk.CLIENT_SetAutoReconnect(haveReConnect, null);

		//设置登录超时时间和尝试次数，可选
		int waitTime = 5000; //登录请求响应超时时间设置为5S
		int tryTimes = 1;    //登录时尝试建立链接1次
		netsdk.CLIENT_SetConnectTime(waitTime, tryTimes);

		// 设置更多网络参数，NET_PARAM的nWaittime，nConnectTryNum成员与CLIENT_SetConnectTime
		// 接口设置的登录设备超时时间和尝试次数意义相同,可选
		NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
		netParam.nConnectTime = 10000;      // 登录时尝试建立链接的超时时间
		netParam.nGetConnInfoTime = 3000;   // 设置子连接的超时时间
		netsdk.CLIENT_SetNetworkParam(netParam);

		return true;
	}
	public boolean remoteCapturePicture(int chn, NetSDKLib netsdk, NetSDKLib.LLong m_hLoginHandle) {
		return snapPicture(chn, 0, 0, netsdk, m_hLoginHandle);
	}
	private boolean snapPicture(int chn, int mode, int interval, NetSDKLib netsdk, NetSDKLib.LLong m_hLoginHandle) {
		NetSDKLib.SNAP_PARAMS stuSnapParams = new NetSDKLib.SNAP_PARAMS();
		stuSnapParams.Channel = chn;  			// channel
		stuSnapParams.mode = mode;    			// capture picture mode
		stuSnapParams.Quality = 3;				// picture quality
		stuSnapParams.InterSnap = interval; 	// timer capture piCLIENT_SnapPictureToFilecture time interval
		stuSnapParams.CmdSerial = 0;  			// request serial
		IntByReference reserved = new IntByReference(0);
		if (!netsdk.CLIENT_SnapPictureEx(m_hLoginHandle, stuSnapParams, reserved)) {
			System.out.println("CLIENT_SnapPictureEx Failed!{}" + ToolKits.getErrorCodePrint());
			return false;
		} else {
			System.out.println("CLIENT_SnapPictureEx success");
		}
		return true;
	}
}