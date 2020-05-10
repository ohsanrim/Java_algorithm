package cookierunrun;

import java.util.ArrayList;
// 젤리의 개수는 16개로 저장
class JellyDrop implements Runnable{
	//public static ArrayList <JellyDTO> list;
	public JellyDrop() {
		Jelly.list = new ArrayList <JellyDTO>();
	}
	public void ThreadStart() {
		Thread t= new Thread(this);
		t.start();
	}
	public void run() {
		while(true) {
			//새로운 젤리 셋팅
			if(Jelly.list.size()==16) Jelly.list.remove(0);
			JellyDTO jellyDTO = new JellyDTO();
			jellyDTO.setX(720);
			jellyDTO.setY(330);
			Jelly.list.add(jellyDTO);
			jellyDTO.imageIndex=(int)(Math.random()*2)+1;   //1~2의 난수를 생성하여 코인을 올릴지 젤리를 올릴지 결정
			try {
				Thread.sleep(200);   //초단위로 진행됨
			} catch (InterruptedException e) {
			}
			if(MyFrame2.gameDie==true) break;
		}
	}
}
