package ua.lw0000.navigame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ua.lw0000.navigame.controller.HWController;
import ua.lw0000.navigame.controller.MoneyController;
import ua.lw0000.navigame.controller.MoodController;
import ua.lw0000.navigame.controller.TimeController;
import ua.lw0000.navigame.main.NaviGame;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;

public class GameStateRenderer implements Renderer {

	private MoneyController moneyController;
	private TimeController timeController;
	private MoodController moodController;
	private HWController hwController;
	private Context context;
	private AnchorMouseOverArea btnRequestOvertimes;

	private AnchorMouseOverArea btnHWBasic;
	private AnchorMouseOverArea btnHWAdvanced;
	private AnchorMouseOverArea btnHWSuper;;

	public GameStateRenderer(MoneyController moneyCtrl,
			TimeController timeController, MoodController moodController,
			HWController hwCtrl, Context ctx) {
		this.moneyController = moneyCtrl;
		this.timeController = timeController;
		this.moodController = moodController;
		this.hwController = hwCtrl;
		this.context = ctx;

		btnRequestOvertimes = new AnchorMouseOverArea(
				NaviGame.gameContainer, context.getOvertimeImg(), 0, 90,
				100, 30) {
			@Override
			public void mousePressed(int button, int mx, int my) {				
				super.mousePressed(button, mx, my);
				if (isMouseOver()) {
					moneyController.setOvertime(true);
				}
			}
		};
		btnRequestOvertimes.setAnchorW(Composition.LEFT_PANEL_W);
		btnRequestOvertimes.setAnchorN(Composition.ROOM_NW);
		btnRequestOvertimes.setMouseOverImage(context.getOvertimeHoverImg());
		btnRequestOvertimes.setMouseDownImage(context.getOvertimeImg());

		btnHWBasic = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwBasicDisabledImg(), 0, 160, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {				
				super.mousePressed(button, mx, my);
				if (isMouseOver() && hwController.getHW() != HWController.HW_BASIC) {
					hwController.upgradeHW(HWController.HW_BASIC);					
				}
			}
		};
		btnHWBasic.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWBasic.setAnchorN(Composition.ROOM_NW);
		btnHWAdvanced = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwAdvancedDisabledImg(), 50, 160, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {				
				super.mousePressed(button, mx, my);
				if (isMouseOver() && hwController.getHW() != HWController.HW_ADVANCED) {
					hwController.upgradeHW(HWController.HW_ADVANCED);					
				}
			}
		};
		btnHWAdvanced.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWAdvanced.setAnchorN(Composition.ROOM_NW);
		btnHWSuper = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwSuperDisabledImg(), 100, 160, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {				
				super.mousePressed(button, mx, my);
				if (isMouseOver() && hwController.getHW() != HWController.HW_SUPER) {
					hwController.upgradeHW(HWController.HW_SUPER);					
				}
			}
		};
		btnHWSuper.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWSuper.setAnchorN(Composition.ROOM_NW);
	}

	@Override
	public Image render() throws SlickException {

		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();
		renderSalary(imgGraphics, 0, 0);
		renderHardware(imgGraphics, 0, 130);
		renderCompiler(imgGraphics, 0, 210);
		renderTeamMood(imgGraphics, 0, 260);

		imgGraphics.flush();
		return context.getGenImage().copy();
	}

	private void renderSalary(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getBigFont());
		imgGraphics.drawString("$" + moneyController.getGameState().getMoney(),
				x, y);

		imgGraphics.setFont(context.getMediumFont());
		Color prevColor = imgGraphics.getColor();
		if (moneyController.isOvertime()) {
			imgGraphics.setColor(new Color(255, 14, 69, 255));
		}
		imgGraphics.drawString(
				"Next Salary: $" + moneyController.getTotalSalary(), x, y + 40);
		imgGraphics.setColor(prevColor);

		int timeFromLastSalary = moneyController.getGameState().getTime()
				- timeController.getTimeSinceLastSalary();

		float salaryProgress = (float) (timeFromLastSalary
				* (double) Composition.LEFT_PANEL_WIDTH / (double) TimeController.SALARY_INTERVAL);
		prevColor = imgGraphics.getColor();
		Color prevBg = imgGraphics.getBackground();
		Rectangle outerRect = new Rectangle(x + 1, y + 70,
				Composition.LEFT_PANEL_WIDTH, 10);
		Rectangle innerRect = new Rectangle(x + 2, y + 70,
				Composition.LEFT_PANEL_WIDTH - 2, 9);
		Rectangle progressRect = new Rectangle(x + 1, y + 70, salaryProgress, 9);

		imgGraphics.setColor(Color.gray);
		imgGraphics.draw(outerRect);
		imgGraphics.setColor(Color.black);
		imgGraphics.fill(innerRect);
		imgGraphics.setColor(Color.gray);
		imgGraphics.fill(progressRect);
		imgGraphics.setColor(prevColor);
		imgGraphics.setBackground(prevBg);

		if (!moneyController.isOvertime()) {
			btnRequestOvertimes.setNormalImage(context.getOvertimeImg());
			btnRequestOvertimes
					.setMouseOverImage(context.getOvertimeHoverImg());
			btnRequestOvertimes.setMouseDownImage(context.getOvertimeImg());
		} else {
			btnRequestOvertimes.setNormalImage(context.getOvertimeLockedImg());
			btnRequestOvertimes.setMouseOverImage(context
					.getOvertimeLockedImg());
			btnRequestOvertimes.setMouseDownImage(context
					.getOvertimeLockedImg());
		}

		btnRequestOvertimes.render(NaviGame.gameContainer, imgGraphics);

	}

	private void renderHardware(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		Color prevColor = imgGraphics.getColor();
		imgGraphics.setColor(Color.white);
		imgGraphics.drawString("Hardware upgrade:", x, y);
		//imgGraphics.setFont(context.getPriceFont());
		if (hwController.isEnabled(HWController.HW_ADVANCED)) {
			btnHWAdvanced.setNormalImage(context.getHwAdvancedImg());
			if (hwController.getHW() != HWController.HW_ADVANCED) {
				btnHWAdvanced.setMouseOverImage(context.getHwAdvancedHoverImg());
			} else {
				btnHWAdvanced.setMouseOverImage(context.getHwAdvancedImg());
			}
			btnHWAdvanced.setAcceptingInput(true);
		} else {
			btnHWAdvanced.setNormalImage(context.getHwAdvancedDisabledImg());
			btnHWAdvanced.setMouseOverImage(context.getHwAdvancedDisabledImg());
			btnHWAdvanced.setAcceptingInput(false);
		}
		if (hwController.isEnabled(HWController.HW_SUPER)) {
			btnHWSuper.setNormalImage(context.getHwSuperImg());
			if (hwController.getHW() != HWController.HW_SUPER) {
				btnHWSuper.setMouseOverImage(context.getHwSuperHoverImg());
			} else {
				btnHWSuper.setMouseOverImage(context.getHwSuperImg());
			}
			btnHWSuper.setAcceptingInput(true);
		} else {
			btnHWSuper.setNormalImage(context.getHwSuperDisabledImg());
			btnHWSuper.setMouseOverImage(context.getHwSuperDisabledImg());
			btnHWSuper.setAcceptingInput(false);
		}
		if (hwController.isEnabled(HWController.HW_BASIC)) {
			btnHWBasic.setNormalImage(context.getHwBasicImg());
			if (hwController.getHW() != HWController.HW_BASIC) {
				btnHWBasic.setMouseOverImage(context.getHwBasicHoverImg());
			} else {
				btnHWBasic.setMouseOverImage(context.getHwBasicImg());
			}
			btnHWBasic.setAcceptingInput(true);
		} else {
			btnHWBasic.setNormalImage(context.getHwBasicDisabledImg());
			btnHWBasic.setMouseOverImage(context.getHwBasicDisabledImg());
			btnHWBasic.setAcceptingInput(false);
		}

		btnHWBasic.render(NaviGame.gameContainer, imgGraphics);
		btnHWAdvanced.render(NaviGame.gameContainer, imgGraphics);
		btnHWSuper.render(NaviGame.gameContainer, imgGraphics);
		imgGraphics
				.drawString("$" + HWController.PAYMENT_BASIC, x + 10, y + 30);
		imgGraphics.drawString("$" + HWController.PAYMENT_ADVANCED,
				x + 10 + 50, y + 30);
		imgGraphics.drawString("$" + HWController.PAYMENT_SUPER, x + 10 + 100,
				y + 30);

		float xChk = x;
		if (hwController.getHW() == HWController.HW_ADVANCED) {
			xChk = x + 60;
		} else if (hwController.getHW() == HWController.HW_SUPER) {
			xChk = x + 110;
		} else {
			xChk = x;
		}
		imgGraphics.drawImage(context.getCheckmark(), xChk, y + 60);

		imgGraphics.setColor(prevColor);
	}

	private void renderCompiler(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		imgGraphics.drawString("Navi-Progress:", x, y);

		double compiler = moneyController.getGameState().getCompilerComplete();

		float compilerProgress = (float) (compiler
				* (double) Composition.LEFT_PANEL_WIDTH / 100);
		Color prevColor = imgGraphics.getColor();
		Color prevBg = imgGraphics.getBackground();
		Rectangle outerRect = new Rectangle(x + 1, y + 30,
				Composition.LEFT_PANEL_WIDTH, 10);
		Rectangle innerRect = new Rectangle(x + 2, y + 30,
				Composition.LEFT_PANEL_WIDTH - 2, 9);
		Rectangle progressRect = new Rectangle(x + 1, y + 30, compilerProgress,
				9);

		imgGraphics.setColor(Color.gray);
		imgGraphics.draw(outerRect);
		imgGraphics.setColor(Color.black);
		imgGraphics.fill(innerRect);
		imgGraphics.setColor(Color.gray);
		imgGraphics.fill(progressRect);
		imgGraphics.setColor(prevColor);
		imgGraphics.setBackground(prevBg);
	}

	private void renderTeamMood(Graphics imgGraphics, float x, float y) {
		int devMood = moodController.getDevelopersPerformance();
		int prodMood = moodController.getProductionPerformance();
		renderMood(imgGraphics, "Developers Mood:", devMood, x, y);
		renderMood(imgGraphics, "Production Mood:", prodMood, x, y + 50);
	}

	private void renderMood(Graphics imgGraphics, String title, int mood,
			float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		imgGraphics.drawString(title, x, y);

		float devMoodProgress = (float) (mood
				* (double) (Composition.LEFT_PANEL_WIDTH - 32) / 100);

		if ((float) mood / 100 >= DeveloperRenderer.MOOD_OK_BORDER) {
			context.getMoodOkImg().draw(x + 1 + 8, y + 24);
		} else if ((float) mood / 100 >= DeveloperRenderer.MOOD_AVG_BORDER) {
			context.getMoodAvgImg().draw(x + 1, y + 24);
		} else {
			context.getMoodBadImg().draw(x + 1, y + 24);
		}

		Color prevColor = imgGraphics.getColor();
		Color prevBg = imgGraphics.getBackground();
		Rectangle outerRect = new Rectangle(x + 32 + 1, y + 30,
				Composition.LEFT_PANEL_WIDTH - 32, 10);
		Rectangle innerRect = new Rectangle(x + 32 + 2, y + 30,
				Composition.LEFT_PANEL_WIDTH - 2 - 32, 9);
		Rectangle progressRect = new Rectangle(x + 32 + 1, y + 30,
				devMoodProgress, 9);
		imgGraphics.setColor(Color.gray);
		imgGraphics.draw(outerRect);
		imgGraphics.setColor(Color.black);
		imgGraphics.fill(innerRect);
		imgGraphics.setColor(Color.gray);
		imgGraphics.fill(progressRect);
		imgGraphics.setColor(prevColor);
		imgGraphics.setBackground(prevBg);
		// imgGraphics.drawString("" + mood, x, y+10);
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop
	}
}
