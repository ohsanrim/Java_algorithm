import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
class DrCanvas extends Canvas {
	private MsPaint msPaint;
	private ArrayList<ShapeDTO> list;
	private Graphics g;

	public DrCanvas(MsPaint msPaint) {
		this.setBackground(new Color(239, 240, 217));
		this.msPaint = msPaint;
		list = new ArrayList<ShapeDTO>();
	}

	@Override
	public void paint(Graphics g) {
		list = msPaint.saveList();
		saveShape(list);
		// 좌표
		int x1 = Integer.parseInt(msPaint.getX1T().getText().trim());
		int x2 = Integer.parseInt(msPaint.getX2T().getText().trim());
		int y1 = Integer.parseInt(msPaint.getY1T().getText().trim());
		int y2 = Integer.parseInt(msPaint.getY2T().getText().trim());
		int z1 = Integer.parseInt(msPaint.getZ1T().getText().trim());
		int z2 = Integer.parseInt(msPaint.getZ2T().getText().trim());
		int x1Temp = msPaint.getx1Temp(); // 초기값 생성
		int y1Temp = msPaint.gety1Temp(); // 초기값 생성

		// 색
		switch (msPaint.getComboIndex()) {
		// this.setForeground(new Color(255,0,0)); break; 로 써도 색이 입혀진다.
		case 0:
			g.setColor(new Color(255, 0, 0));
			break;
		case 1:
			g.setColor(new Color(34, 177, 76));
			break;
		case 2:
			g.setColor(new Color(0, 0, 255));
			break;
		case 3:
			g.setColor(new Color(160, 50, 143));
			break;
		case 4:
			g.setColor(new Color(91, 198, 198));
			break;
		}
		// 도형
		if (msPaint.getCheckBox() == true) {
			if (msPaint.getRadio() == 0)
				g.drawLine(x1, y1, x2, y2);
			else if (msPaint.getRadio() == 1) {
				g.fillOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
			} else if (msPaint.getRadio() == 2) {
				g.fillRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
			} else if (msPaint.getRadio() == 3)
				g.fillRoundRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1), z1, z2);
		} else {
			if (msPaint.getRadio() == 0)
				g.drawLine(x1, y1, x2, y2);
			else if (msPaint.getRadio() == 1)
				g.drawOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
			else if (msPaint.getRadio() == 2)
				g.drawRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
			else if (msPaint.getRadio() == 3)
				g.drawRoundRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1), z1, z2);
		}

	}

	public void saveShape(ArrayList<ShapeDTO> list) {
		// 그 전에 그렸던 그림들 호출
		g = getGraphics();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int x1 = list.get(i).getX1();
				int x2 = list.get(i).getX2();
				int y1 = list.get(i).getY1();
				int y2 = list.get(i).getY2();
				int z1 = list.get(i).getZ1();
				int z2 = list.get(i).getZ2();
				Vector<Integer> vector = list.get(i).getVector();
				switch (list.get(i).getColor()) {
				// this.setForeground(new Color(255,0,0)); break; 로 써도 색이 입혀진다.
				case 0:
					g.setColor(new Color(255, 0, 0));
					break;
				case 1:
					g.setColor(new Color(34, 177, 76));
					break;
				case 2:
					g.setColor(new Color(0, 0, 255));
					break;
				case 3:
					g.setColor(new Color(160, 50, 143));
					break;
				case 4:
					g.setColor(new Color(91, 198, 198));
					break;
				}
				if (list.get(i).getCheckBox() == true) {
					if (list.get(i).getRadio() == 0)
						g.drawLine(x1, y1, x2, y2);
					else if (list.get(i).getRadio() == 1) {
						g.fillOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
					} else if (list.get(i).getRadio() == 2) {
						g.fillRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
					} else if (list.get(i).getRadio() == 3)
						g.fillRoundRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1), z1,
								z2);

					else if (list.get(i).getRadio() == 4) {
						int j = 0;
						while (true) {
							if (vector.size() - 4 == j)
								break;
							g.drawLine(vector.get(j), vector.get(j + 1), vector.get(j + 2), vector.get(j + 3));
							j += 2;
						}
					}

				} else {
					if (list.get(i).getRadio() == 0)
						g.drawLine(x1, y1, x2, y2);
					else if (list.get(i).getRadio() == 1)
						g.drawOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
					else if (list.get(i).getRadio() == 2)
						g.drawRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1));
					else if (list.get(i).getRadio() == 3)
						g.drawRoundRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, Math.abs(x2 - x1), Math.abs(y2 - y1), z1,
								z2);

					else if (list.get(i).getRadio() == 4) {
						int j = 0;
						while (true) {
							if (vector.size() - 4 == j)
								break;
							g.drawLine(vector.get(j), vector.get(j + 1), vector.get(j + 2), vector.get(j + 3));
							j += 2;
						}
					}
				}
			}
		}
	}
}