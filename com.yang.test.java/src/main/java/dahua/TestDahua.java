package dahua;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.sun.jna.Pointer;

import main.java.com.netsdk.common.SavePath;
import main.java.com.netsdk.demo.module.CapturePictureModule;
import main.java.com.netsdk.demo.module.LoginModule;
import main.java.com.netsdk.lib.NetSDKLib;
import main.java.com.netsdk.lib.NetSDKLib.LLong;

public class TestDahua {

	private static DisConnect disConnect = new DisConnect();
	private static HaveReConnect haveReConnect = new HaveReConnect();
	private CountDownLatch latch = null;

	public static void main(String[] args) throws InterruptedException {
		new TestDahua().doIt();
	}

	public void doIt() throws InterruptedException {
		latch = new CountDownLatch(1);
		
		LoginModule.init(disConnect, haveReConnect);
		
		//Native.setCallbackThreadInitializer(m_CaptureReceiveCB, new CallbackThreadInitializer(false, false, "snapPicture callback thread"));
		boolean result = LoginModule.login("124.160.79.205", 61574, "admin", "admin123");
		System.out.println("登陆结果：" + result);
		

		CapturePictureModule.setSnapRevCallBack(m_CaptureReceiveCB);
		

		LLong m_hPlayHandle = LoginModule.netsdk.CLIENT_RealPlayEx(LoginModule.m_hLoginHandle, 0, null, 0);
		CapturePictureModule.remoteCapturePicture(0);
		latch.await(60, TimeUnit.SECONDS);
		
		latch = new CountDownLatch(1);
		CapturePictureModule.remoteCapturePicture(0);
		latch.await(60, TimeUnit.SECONDS);
		
		latch = new CountDownLatch(1);
		CapturePictureModule.remoteCapturePicture(0);
		latch.await(60, TimeUnit.SECONDS);
		
		LoginModule.netsdk.CLIENT_StopRealPlayEx(m_hPlayHandle);
		LoginModule.logout();
		LoginModule.cleanup();
	}

	public fCaptureReceiveCB m_CaptureReceiveCB = new fCaptureReceiveCB();
	public class fCaptureReceiveCB implements NetSDKLib.fSnapRev {
		BufferedImage bufferedImage = null;

		public void invoke(LLong lLoginID, Pointer pBuf, int RevLen, int EncodeType, int CmdSerial, Pointer dwUser) {
			if (pBuf != null && RevLen > 0) {
				String strFileName = SavePath.getSavePath().getSaveCapturePath();

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
}